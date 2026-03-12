package ch.uhc_yetis.nightmanager.infrastructure.oidc;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
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

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Provides beans for the Spring Authorization Server.
 * Because we define custom SecurityFilterChain beans (resource server, default
 * chain),
 * the auto-configured auth server filter chain
 * (@ConditionalOnDefaultWebSecurity) backs off.
 * We therefore explicitly create the authorization server security filter chain
 * here.
 */
@Configuration
public class AuthorizationServerConfig {

    @Value("${nightmanager.auth.issuer-url}")
    private String issuerUrl;

    @Value("${nightmanager.auth.client.redirect-uris}")
    private String redirectUris;

    @Value("${nightmanager.auth.client.post-logout-redirect-uris}")
    private String postLogoutRedirectUris;

    /**
     * Authorization Server security filter chain — handles OAuth2/OIDC protocol endpoints.
     * This must run at highest precedence so that /oauth2/**, /.well-known/**, /connect/**
     * requests are handled by the authorization server before any other filter chain.
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
        .oauth2AuthorizationServer(oauthServer -> {
            				http.securityMatcher(oauthServer.getEndpointsMatcher());
                            oauthServer.oidc(Customizer.withDefaults());
        }
        ).authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
        .exceptionHandling(exceptions -> exceptions
                .defaultAuthenticationEntryPointFor(
                        new LoginUrlAuthenticationEntryPoint("/login"),
                        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)));
        return http.build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient.Builder builder = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("nightmanager-client")
                .clientName("Nightmanager Client")
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE) // Public client (SPA with PKCE)
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

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer(issuerUrl)
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate RSA key pair", e);
        }
    }

    /**
     * Customizes JWT tokens to include resolved permissions and display name.
     * The 'permissions' claim is a flat list of all permission strings the user
     * has, derived recursively from their assigned roles.
     */
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(
            ApplicationUserRepository userRepository,
            ch.uhc_yetis.nightmanager.application.PermissionService permissionService) {
        return context -> {
            var authentication = context.getPrincipal();

            // For Microsoft/OIDC social login, getPrincipal() is an OAuth2AuthenticationToken
            // whose getName() returns the 'sub' claim (MS user ID), NOT the email.
            // For local login, getName() is the email (set as username in UserDetailsServiceImpl).
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
