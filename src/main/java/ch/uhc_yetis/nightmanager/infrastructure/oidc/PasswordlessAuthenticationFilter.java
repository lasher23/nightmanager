package ch.uhc_yetis.nightmanager.infrastructure.oidc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

/**
 * Custom filter that intercepts POST /login with email + verification code
 * and delegates to {@link PasswordlessAuthenticationProvider}.
 */
public class PasswordlessAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public PasswordlessAuthenticationFilter() {
        super((request) -> "POST".equals(request.getMethod()) && "/login".equals(request.getServletPath()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        PasswordlessAuthenticationToken token = new PasswordlessAuthenticationToken(email, code);
        return getAuthenticationManager().authenticate(token);
    }
}
