package ch.uhc_yetis.nightmanager.infrastructure.auth.email;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.model.VerificationCode;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import ch.uhc_yetis.nightmanager.domain.repository.VerificationCodeRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Validates the email + verification code and returns an authenticated
 * {@link EmailCodeAuthenticationToken} on success.
 */
@Component
public class EmailCodeAuthenticationProvider implements AuthenticationProvider {

    private final VerificationCodeRepository verificationCodeRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public EmailCodeAuthenticationProvider(VerificationCodeRepository verificationCodeRepository,
                                           ApplicationUserRepository applicationUserRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String code = (String) authentication.getCredentials();

        VerificationCode verificationCode = verificationCodeRepository
                .findTopByEmailAndUsedFalseOrderByExpiresAtDesc(email)
                .orElseThrow(() -> new BadCredentialsException("No verification code found"));

        if (verificationCode.isExpired()) {
            throw new BadCredentialsException("Verification code has expired");
        }
        if (!verificationCode.getCode().equals(code)) {
            throw new BadCredentialsException("Invalid verification code");
        }

        verificationCode.setUsed(true);
        verificationCodeRepository.save(verificationCode);

        ApplicationUser user = applicationUserRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        if (!user.isEnabled()) {
            throw new BadCredentialsException("User account is disabled");
        }

        List<GrantedAuthority> authorities = new ArrayList<>(user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
        authorities.add(FactorGrantedAuthority.withFactor("EMAIL_CODE").issuedAt(Instant.now()).build());

        return new EmailCodeAuthenticationToken(email, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
