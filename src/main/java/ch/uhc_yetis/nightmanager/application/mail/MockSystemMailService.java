package ch.uhc_yetis.nightmanager.application.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Local-development stand-in for {@link GraphSystemMailService}: instead of actually sending
 * mail via Microsoft Graph, it just logs a short summary to the console. Enabled by setting
 * {@code nightmanager.mail.mock-enabled=true}.
 */
@Service
@ConditionalOnProperty(name = "nightmanager.mail.mock-enabled", havingValue = "true")
public class MockSystemMailService implements SystemMailSender {

    private static final Logger log = LoggerFactory.getLogger(MockSystemMailService.class);

    @Override
    public void sendMail(String subject, String content, String... toRecipients) {
        log.info(MailConsolePrinter.format("system", subject, content, toRecipients));
    }

    @Override
    public void sendHtmlMail(String subject, String htmlContent, String... toRecipients) {
        log.info(MailConsolePrinter.format("system", subject, htmlContent, toRecipients));
    }
}
