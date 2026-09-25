package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.application.mail.SystemMailSender;
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
    private final SystemMailSender graphSystemMailService;
    private final SecureRandom secureRandom = new SecureRandom();

    public PasswordlessAuthService(VerificationCodeRepository verificationCodeRepository,
                                   ApplicationUserRepository applicationUserRepository,
                                   SystemMailSender graphSystemMailService) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.graphSystemMailService = graphSystemMailService;
    }

    /**
     * Sends a verification code to the given email address.
     * A new ApplicationUser is provisioned on first request, mirroring OIDC login behavior.
     */
    public boolean sendVerificationCode(String email) {
        ApplicationUser user = applicationUserRepository.findByEmail(email);
        if (user == null) {
            user = new ApplicationUser();
            user.setEmail(email);
            user.setUsername(email);
            user.setEnabled(true);
            user.setRoles(java.util.Set.of("USER"));
            user = applicationUserRepository.save(user);
            log.info("Provisioned new user on first email-code login request: {}", email);
        }
        if (!user.isEnabled()) {
            log.warn("Verification code requested for disabled email: {}", email);
            return true; // Don't reveal account state
        }

        String code = generateCode();

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setCode(code);
        verificationCode.setExpiresAt(Instant.now().plus(CODE_EXPIRY_MINUTES, ChronoUnit.MINUTES));
        verificationCodeRepository.save(verificationCode);

        try {
            graphSystemMailService.sendHtmlMail(
                    "Dein Anmeldecode für Nightmanager",
                    buildVerificationEmailHtml(code),
                    email);
        } catch (Exception e) {
            log.error("Failed to send verification email to {}", email, e);
        }

        return true;
    }

    private String buildVerificationEmailHtml(String code) {
        String codeDigitsHtml = code.chars()
                .mapToObj(c -> "<span style=\"display:inline-block;width:36px;height:44px;line-height:44px;"
                        + "margin:0 4px;background:#1a1a2e;color:#ffffff;border-radius:8px;"
                        + "font-size:22px;font-weight:700;text-align:center;"
                        + "font-family:'Courier New',monospace;\">" + (char) c + "</span>")
                .collect(java.util.stream.Collectors.joining());

        return """
                <!DOCTYPE html>
                <html lang="de">
                <body style="margin:0;padding:0;background:#f2f2f7;font-family:'Segoe UI',Arial,sans-serif;">
                  <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="background:#f2f2f7;padding:32px 0;">
                    <tr>
                      <td align="center">
                        <table role="presentation" width="480" cellpadding="0" cellspacing="0"
                               style="background:#ffffff;border-radius:16px;overflow:hidden;box-shadow:0 8px 24px rgba(0,0,0,0.08);">
                          <tr>
                            <td style="background:linear-gradient(135deg,#4f46e5,#7c3aed);padding:28px 32px;">
                              <span style="color:#ffffff;font-size:20px;font-weight:700;letter-spacing:0.5px;">� Nightmanager</span>
                            </td>
                          </tr>
                          <tr>
                            <td style="padding:32px;">
                              <h1 style="margin:0 0 12px;font-size:22px;color:#1a1a2e;">Dein Anmeldecode</h1>
                              <p style="margin:0 0 24px;font-size:15px;line-height:1.6;color:#555;">
                                Verwende den folgenden Code, um dich bei Nightmanager anzumelden. Der Code ist
                                <strong>%d Minuten</strong> gültig.
                              </p>
                              <div style="text-align:center;margin:28px 0;">%s</div>
                              <p style="margin:24px 0 0;font-size:13px;line-height:1.6;color:#888;">
                                Falls du diese Anmeldung nicht angefordert hast, kannst du diese E-Mail einfach ignorieren.
                              </p>
                            </td>
                          </tr>
                          <tr>
                            <td style="background:#fafafa;padding:16px 32px;border-top:1px solid #eee;">
                              <p style="margin:0;font-size:12px;color:#aaa;">Diese E-Mail wurde automatisch generiert.</p>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </body>
                </html>
                """.formatted(CODE_EXPIRY_MINUTES, codeDigitsHtml);
    }

    private String generateCode() {
        int code = secureRandom.nextInt(900000) + 100000; // 6-digit code
        return String.valueOf(code);
    }
}
