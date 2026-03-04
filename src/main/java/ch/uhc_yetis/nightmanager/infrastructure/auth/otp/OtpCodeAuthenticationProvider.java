package ch.uhc_yetis.nightmanager.infrastructure.auth.otp;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Validates one-time login codes (e.g. generated QR code logins).
 * TODO: implement actual OTP code lookup and validation.
 */
@Component
public class OtpCodeAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO: look up the OTP code in the database, verify it hasn't been used,
        //       resolve the linked user and return an authenticated token.
        throw new BadCredentialsException("OTP login not yet implemented");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
