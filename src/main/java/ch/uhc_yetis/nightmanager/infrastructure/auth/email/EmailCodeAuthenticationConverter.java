package ch.uhc_yetis.nightmanager.infrastructure.auth.email;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

/**
 * Reads login_type=email_code, email, and code from a POST /login request
 * and produces an {@link EmailCodeAuthenticationToken}.
 * Returns null for any other login type so the next converter in the
 * DelegatingAuthenticationConverter chain gets a chance.
 */
public class EmailCodeAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        if (!"email_code".equals(request.getParameter("login_type"))) {
            return null;
        }
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        if (email == null || email.isBlank() || code == null || code.isBlank()) {
            return null;
        }
        return new EmailCodeAuthenticationToken(email.trim().toLowerCase(), code.trim());
    }
}
