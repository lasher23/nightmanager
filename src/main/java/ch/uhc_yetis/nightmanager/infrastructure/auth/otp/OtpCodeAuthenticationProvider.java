package ch.uhc_yetis.nightmanager.infrastructure.auth.otp;

import ch.uhc_yetis.nightmanager.application.PermissionService;
import ch.uhc_yetis.nightmanager.domain.model.OtpUser;
import ch.uhc_yetis.nightmanager.domain.repository.OtpUserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Validates OTP login tokens.
 * Looks up the token in the {@code otp_user} table, checks that it has not expired,
 * and returns an authenticated token whose principal is the OTP user's name.
 */
@Component
public class OtpCodeAuthenticationProvider implements AuthenticationProvider {

    private final OtpUserRepository otpUserRepository;
    private final PermissionService permissionService;

    public OtpCodeAuthenticationProvider(OtpUserRepository otpUserRepository,
                                          PermissionService permissionService) {
        this.otpUserRepository = otpUserRepository;
        this.permissionService = permissionService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();

        OtpUser otpUser = otpUserRepository.findByToken(token)
                .orElseThrow(() -> new BadCredentialsException("Invalid OTP token"));

        if (otpUser.isExpired()) {
            throw new DisabledException("OTP token has expired");
        }

        var permissions = permissionService.resolvePermissions(otpUser.getRoles());

        return new OtpCodeAuthenticationToken(
                otpUser.getName(),
                token,
                permissions.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
