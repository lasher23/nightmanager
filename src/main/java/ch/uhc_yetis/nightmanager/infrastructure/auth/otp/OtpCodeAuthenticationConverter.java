package ch.uhc_yetis.nightmanager.infrastructure.auth.otp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

/**
 * Reads login_type=otp and otp_code from a POST /login request.
 * Returns null for any other login type.
 */
public class OtpCodeAuthenticationConverter implements AuthenticationConverter {

    @Override
    public Authentication convert(HttpServletRequest request) {
        if (!"otp".equals(request.getParameter("login_type"))) {
            return null;
        }
        String otpCode = request.getParameter("otp_code");
        if (otpCode == null || otpCode.isBlank()) {
            return null;
        }
        return new OtpCodeAuthenticationToken(otpCode.trim());
    }
}
