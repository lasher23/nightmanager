package ch.uhc_yetis.nightmanager.infrastructure;

import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import ch.uhc_yetis.nightmanager.domain.repository.VerificationCodeRepository;
import ch.uhc_yetis.nightmanager.infrastructure.oidc.PasswordlessAuthenticationFilter;
import ch.uhc_yetis.nightmanager.infrastructure.oidc.PasswordlessAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
public class WebSecurity {

    @Value("${nightmanager.auth.cors.allowed-origins}")
    private String allowedOrigins;

    private final VerificationCodeRepository verificationCodeRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public WebSecurity(VerificationCodeRepository verificationCodeRepository,
                       ApplicationUserRepository applicationUserRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    /**
     * Resource Server filter chain — protects /api/** with JWT bearer tokens.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/api/**")
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Individual endpoints use @PreAuthorize
                )
                .build();
    }

    /**
     * Default filter chain — handles login page, passwordless auth, static resources, and SPA routes.
     */
    @Bean
    @Order(3)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        PasswordlessAuthenticationProvider provider = new PasswordlessAuthenticationProvider(
                verificationCodeRepository, applicationUserRepository);
        AuthenticationManager authenticationManager = new ProviderManager(provider);

        PasswordlessAuthenticationFilter passwordlessFilter = new PasswordlessAuthenticationFilter();
        passwordlessFilter.setAuthenticationManager(authenticationManager);
        passwordlessFilter.setAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler());
        passwordlessFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));

        // Only save /oauth2/authorize requests as the "return-to" URL after login.
        // This prevents random browser background requests (e.g. Chrome DevTools discovery at
        // /.well-known/appspecific/com.chrome.devtools.json) from overwriting the saved OIDC
        // authorization request in the session and causing a redirect to an unexpected URL.
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setRequestMatcher(request -> "/oauth2/authorize".equals(request.getRequestURI()));

        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .requestCache(cache -> cache.requestCache(requestCache))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/login.html", "/auth/passwordless/**", "/error", "/.well-known/**").permitAll()
                        .requestMatchers(
                                "/", "/index.html", "/oidc/**",
                                "/*.js", "/*.css", "/*.ico", "/*.webmanifest",
                                "/assets/**", "/ngsw*", "/safety-worker.js",
                                "/chunk-*.js", "/main-*.js", "/polyfills-*.js", "/styles-*.css",
                                "/referee/**", "/display/**", "/admin/**", "/v2/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterAt(passwordlessFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Converts the "roles" claim from JWT access tokens into Spring Security GrantedAuthority objects.
     * This allows @PreAuthorize("hasAuthority('ADMIN')") to work with OIDC tokens.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Global CORS filter registered at servlet level — ensures CORS headers are set
     * for ALL endpoints including the Authorization Server's OAuth2/OIDC endpoints
     * (token, discovery, jwks) which are needed for cross-origin requests from the SPA in development.
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
