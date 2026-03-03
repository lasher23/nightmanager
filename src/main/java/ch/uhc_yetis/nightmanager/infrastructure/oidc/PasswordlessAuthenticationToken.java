package ch.uhc_yetis.nightmanager.infrastructure.oidc;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PasswordlessAuthenticationToken extends AbstractAuthenticationToken {

    private final String email;
    private final String code;

    /**
     * Unauthenticated token (before verification).
     */
    public PasswordlessAuthenticationToken(String email, String code) {
        super((Collection<? extends GrantedAuthority>) null);
        this.email = email;
        this.code = code;
        setAuthenticated(false);
    }

    /**
     * Authenticated token (after successful verification).
     */
    public PasswordlessAuthenticationToken(String email, Collection<? extends GrantedAuthority> authorities) {
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
