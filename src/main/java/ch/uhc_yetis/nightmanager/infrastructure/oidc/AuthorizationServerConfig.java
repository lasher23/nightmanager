package ch.uhc_yetis.nightmanager.infrastructure.oidc;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.time.Duration;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Provides beans for the Spring Authorization Server.
 *
 * Key design decisions:
 * - RSA signing key is persisted to disk so tokens remain valid across restarts.
 * - OAuth2 authorizations (refresh tokens) are stored in PostgreSQL via
 *   JdbcOAuth2AuthorizationService so they survive restarts.
 * - The registered client uses a stable ID so the JDBC store can resolve it.
 */
@Configuration
public class AuthorizationServerConfig {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationServerConfig.class);

    /** Stable ID — must never change once the database contains authorizations. */
    private static final String CLIENT_ID = "nightmanager-client";
    private static final String REGISTERED_CLIENT_DB_ID = "nightmanager-v1";

    @Value("${nightmanager.auth.issuer-url}")
    private String issuerUrl;

    @Value("${nightmanager.auth.client.redirect-uris}")
    private String redirectUris;

    @Value("${nightmanager.auth.client.post-logout-redirect-uris}")
    private String postLogoutRedirectUris;

    /**
     * Directory where the RSA private/public PEM files are stored.
     * Defaults to a {@code keys/} subdirectory next to the working directory.
     */
    @Value("${nightmanager.auth.key-store-dir:./keys}")
    private String keyStoreDir;

    // ── Authorization Server filter chain ────────────────────────────────────

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .oauth2AuthorizationServer(oauthServer -> {
                http.securityMatcher(oauthServer.getEndpointsMatcher());
                oauthServer.oidc(Customizer.withDefaults());
            })
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            .exceptionHandling(exceptions -> exceptions
                    .defaultAuthenticationEntryPointFor(
                            new LoginUrlAuthenticationEntryPoint("/login"),
                            new MediaTypeRequestMatcher(MediaType.TEXT_HTML)));
        return http.build();
    }

    // ── Registered client ─────────────────────────────────────────────────────

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient.Builder builder = RegisteredClient
                .withId(REGISTERED_CLIENT_DB_ID)          // stable — do NOT use randomUUID here
                .clientId(CLIENT_ID)
                .clientName("Nightmanager Client")
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE) // public PKCE client
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN);

        for (String uri : redirectUris.split(",")) {
            builder.redirectUri(uri.trim());
        }
        for (String uri : postLogoutRedirectUris.split(",")) {
            builder.postLogoutRedirectUri(uri.trim());
        }

        RegisteredClient nightmanagerClient = builder
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope(OidcScopes.EMAIL)
                .scope("offline_access")
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(true)
                        .requireAuthorizationConsent(false)
                        .build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(5))
                        .refreshTokenTimeToLive(Duration.ofDays(14))
                        .reuseRefreshTokens(false)
                        .build())
                .build();

        return new InMemoryRegisteredClientRepository(nightmanagerClient);
    }

    // ── Authorization server settings ─────────────────────────────────────────

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer(issuerUrl)
                .build();
    }

    // ── RSA signing key — persistent across restarts ──────────────────────────

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = loadOrGenerateRsaKey();
        return new ImmutableJWKSet<>(new JWKSet(rsaKey));
    }

    /**
     * Loads the RSA-2048 key pair from {@code <keyStoreDir>/rsa-private.pem}
     * and {@code rsa-public.pem}.
     * If either file is missing a new key pair is generated and both files are
     * written to disk.
     */
    private RSAKey loadOrGenerateRsaKey() {
        Path dir        = Path.of(keyStoreDir);
        Path privatePem = dir.resolve("rsa-private.pem");
        Path publicPem  = dir.resolve("rsa-public.pem");

        if (Files.exists(privatePem) && Files.exists(publicPem)) {
            try {
                byte[] privDer = decodePem(Files.readString(privatePem, StandardCharsets.UTF_8));
                byte[] pubDer  = decodePem(Files.readString(publicPem,  StandardCharsets.UTF_8));

                KeyFactory kf = KeyFactory.getInstance("RSA");
                RSAPrivateKey privateKey = (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(privDer));
                RSAPublicKey  publicKey  = (RSAPublicKey)  kf.generatePublic(new X509EncodedKeySpec(pubDer));

                log.info("Loaded RSA signing key from {}", dir.toAbsolutePath());
                return new RSAKey.Builder(publicKey)
                        .privateKey(privateKey)
                        .keyID("nightmanager-signing-key")
                        .build();
            } catch (Exception e) {
                log.warn("Failed to load existing RSA key — regenerating. Cause: {}", e.getMessage());
            }
        }

        // Generate and persist
        try {
            Files.createDirectories(dir);
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(2048);
            KeyPair keyPair = gen.generateKeyPair();

            Files.writeString(privatePem, encodePem("PRIVATE KEY", keyPair.getPrivate().getEncoded()), StandardCharsets.UTF_8);
            Files.writeString(publicPem,  encodePem("PUBLIC KEY",  keyPair.getPublic().getEncoded()),  StandardCharsets.UTF_8);

            log.info("Generated new RSA signing key and saved to {}", dir.toAbsolutePath());
            return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                    .privateKey((RSAPrivateKey) keyPair.getPrivate())
                    .keyID("nightmanager-signing-key")
                    .build();
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to persist RSA key pair", e);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate RSA key pair", e);
        }
    }

    private static String encodePem(String type, byte[] encoded) {
        String base64 = Base64.getMimeEncoder(64, new byte[]{'\n'}).encodeToString(encoded);
        return "-----BEGIN " + type + "-----\n" + base64 + "\n-----END " + type + "-----\n";
    }

    private static byte[] decodePem(String pem) {
        String stripped = pem
                .replaceAll("-----BEGIN [^-]+-----", "")
                .replaceAll("-----END [^-]+-----", "")
                .replaceAll("\\s+", "");
        return Base64.getDecoder().decode(stripped);
    }

    // ── JWT token customizer ──────────────────────────────────────────────────

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(
            ApplicationUserRepository userRepository,
            ch.uhc_yetis.nightmanager.application.PermissionService permissionService) {
        return context -> {
            var authentication = context.getPrincipal();

            String email = null;
            if (authentication instanceof org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken oauth2Token) {
                email = oauth2Token.getPrincipal().getAttribute("email");
            }
            if (email == null || email.isBlank()) {
                email = authentication.getName();
            }

            ApplicationUser user = userRepository.findByEmail(email);
            if (user != null) {
                var permissions = permissionService.resolvePermissions(user.getRoles());
                context.getClaims().claim("permissions", permissions);
                context.getClaims().claim("preferred_username", user.getUsername());
                context.getClaims().claim("email", email);
            }
        };
    }
}
