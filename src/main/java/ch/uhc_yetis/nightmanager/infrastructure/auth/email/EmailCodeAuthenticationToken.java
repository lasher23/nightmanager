package ch.uhc_yetis.nightmanager.infrastructure.auth.email;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Authentication token for the email + verification code login method.
 * Two constructors: unauthenticated (pre-verification) and authenticated (post-verification).
 */
public class EmailCodeAuthenticationToken extends AbstractAuthenticationToken {

    private final String email;
    private final String code;

    /** Unauthenticated — before the code is verified. */
    public EmailCodeAuthenticationToken(String email, String code) {
        super((Collection<? extends GrantedAuthority>) null);
        this.email = email;
        this.code = code;
        setAuthenticated(false);
    }

    /** Authenticated — after successful verification. */
    public EmailCodeAuthenticationToken(String email, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.email = email;
        this.code = null;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return code;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }
}
