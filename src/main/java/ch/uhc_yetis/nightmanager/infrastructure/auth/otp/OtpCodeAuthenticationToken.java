package ch.uhc_yetis.nightmanager.infrastructure.auth.otp;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Authentication token for OTP (QR code / token) login.
 * Principal  = OTP user name (human-readable label).
 * Credentials = raw token UUID string.
 */
public class OtpCodeAuthenticationToken extends AbstractAuthenticationToken {

    private final String name;
    private final String otpCode;

    /** Unauthenticated — before the token is verified. */
    public OtpCodeAuthenticationToken(String otpCode) {
        super((Collection<? extends GrantedAuthority>) null);
        this.name    = otpCode;
        this.otpCode = otpCode;
        setAuthenticated(false);
    }

    /** Authenticated — after successful verification. */
    public OtpCodeAuthenticationToken(String name, String otpCode,
                                       Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.name    = name;
        this.otpCode = otpCode;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return otpCode;
    }

    @Override
    public Object getPrincipal() {
        return name;
    }
}
