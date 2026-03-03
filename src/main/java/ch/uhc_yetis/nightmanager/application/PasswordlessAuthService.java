package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.model.VerificationCode;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import ch.uhc_yetis.nightmanager.domain.repository.VerificationCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class PasswordlessAuthService {

    private static final Logger log = LoggerFactory.getLogger(PasswordlessAuthService.class);
    private static final int CODE_EXPIRY_MINUTES = 10;

    private final VerificationCodeRepository verificationCodeRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    public PasswordlessAuthService(VerificationCodeRepository verificationCodeRepository,
                                   ApplicationUserRepository applicationUserRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    /**
     * Sends a verification code to the given email address.
     * Returns true regardless of whether the email exists (to prevent email enumeration).
     */
    public boolean sendVerificationCode(String email) {
        ApplicationUser user = applicationUserRepository.findByEmail(email);
        if (user == null || !user.isEnabled()) {
            log.warn("Verification code requested for unknown or disabled email: {}", email);
            return true; // Don't reveal whether email exists
        }

        String code = generateCode();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setCode(code);
        verificationCode.setExpiresAt(Instant.now().plus(CODE_EXPIRY_MINUTES, ChronoUnit.MINUTES));
        verificationCodeRepository.save(verificationCode);

        // TODO: Replace with actual email sending (e.g. Spring Mail, SendGrid, etc.)
        log.info("========================================");
        log.info("  VERIFICATION CODE for {}: {}", email, code);
        log.info("========================================");

        return true;
    }

    private String generateCode() {
        int code = secureRandom.nextInt(900000) + 100000; // 6-digit code
        return String.valueOf(code);
    }
}
