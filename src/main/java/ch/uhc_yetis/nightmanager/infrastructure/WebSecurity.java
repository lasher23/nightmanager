package ch.uhc_yetis.nightmanager.infrastructure;

import ch.uhc_yetis.nightmanager.application.UserDetailsServiceImpl;
import ch.uhc_yetis.nightmanager.infrastructure.auth.email.EmailCodeAuthenticationConverter;
import ch.uhc_yetis.nightmanager.infrastructure.auth.email.EmailCodeAuthenticationProvider;
import ch.uhc_yetis.nightmanager.infrastructure.auth.otp.OtpCodeAuthenticationConverter;
import ch.uhc_yetis.nightmanager.infrastructure.auth.otp.OtpCodeAuthenticationProvider;
import ch.uhc_yetis.nightmanager.infrastructure.auth.password.PasswordAuthenticationConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.DelegatingAuthenticationConverter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
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

    private final EmailCodeAuthenticationProvider emailCodeProvider;
    private final OtpCodeAuthenticationProvider otpCodeProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public WebSecurity(EmailCodeAuthenticationProvider emailCodeProvider,
                       OtpCodeAuthenticationProvider otpCodeProvider,
                       UserDetailsServiceImpl userDetailsService) {
        this.emailCodeProvider = emailCodeProvider;
        this.otpCodeProvider = otpCodeProvider;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Single app filter chain.
     * Authentication at POST /login is handled by AuthenticationFilter, which delegates to a
     * ProviderManager containing all login method providers. Adding a new login method only
     * requires a new AuthenticationConverter + AuthenticationProvider pair.
     * /api/** endpoints additionally accept JWT bearer tokens via oauth2ResourceServer.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception {
        // --- providers ---
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider(userDetailsService);
        daoProvider.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());

        ProviderManager providerManager = new ProviderManager(
                emailCodeProvider,
                otpCodeProvider,
                daoProvider
        );

        // --- converters: each returns null if login_type doesn't match, next one is tried ---
        DelegatingAuthenticationConverter converter = new DelegatingAuthenticationConverter(List.of(
                new EmailCodeAuthenticationConverter(),  // login_type=email_code
                new OtpCodeAuthenticationConverter(),    // login_type=otp
                new PasswordAuthenticationConverter()    // login_type=password
        ));

        // --- single login filter for all methods ---
        AuthenticationFilter loginFilter = new AuthenticationFilter(providerManager, converter);
        loginFilter.setRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher(org.springframework.http.HttpMethod.POST, "/login"));
        loginFilter.setSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler());
        loginFilter.setFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        // Must persist the authenticated context to the session (default is request-scoped only)
        loginFilter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());

        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/", false)
                )
                .exceptionHandling(ex -> ex
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
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
