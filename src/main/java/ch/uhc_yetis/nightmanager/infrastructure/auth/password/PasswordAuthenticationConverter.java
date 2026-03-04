package ch.uhc_yetis.nightmanager.infrastructure.auth.password;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

/**
 * Reads login_type=password, email (used as username), and password from a POST /login request.
 * Produces a standard {@link UsernamePasswordAuthenticationToken} handled by Spring's
 * built-in {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
 * Returns null for any other login type.
 */
public class PasswordAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        if (!"password".equals(request.getParameter("login_type"))) {
            return null;
        }
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || email.isBlank() || password == null) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(email.trim().toLowerCase(), password);
    }
}
