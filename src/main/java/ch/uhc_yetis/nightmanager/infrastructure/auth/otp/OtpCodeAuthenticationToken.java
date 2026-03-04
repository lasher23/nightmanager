package ch.uhc_yetis.nightmanager.infrastructure.auth.otp;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Authentication token for one-time login codes (e.g. QR code login).
 */
public class OtpCodeAuthenticationToken extends AbstractAuthenticationToken {

    private final String otpCode;

    /** Unauthenticated — before the code is verified. */
    public OtpCodeAuthenticationToken(String otpCode) {
        super((Collection<? extends GrantedAuthority>) null);
        this.otpCode = otpCode;
        setAuthenticated(false);
    }

    /** Authenticated — after successful verification. */
    public OtpCodeAuthenticationToken(String otpCode, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.otpCode = otpCode;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return otpCode;
    }

    @Override
    public Object getPrincipal() {
        return otpCode;
    }
}
