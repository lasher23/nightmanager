package ch.uhc_yetis.nightmanager.application.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

/**
 * Local-development stand-in for {@link GraphUserMailService}: instead of actually sending
 * mail via Microsoft Graph, it just logs a short summary to the console. Enabled by setting
 * {@code nightmanager.mail.mock-enabled=true}.
 */
@Service
@ConditionalOnProperty(name = "nightmanager.mail.mock-enabled", havingValue = "true")
public class MockUserMailService implements UserMailSender {

    private static final Logger log = LoggerFactory.getLogger(MockUserMailService.class);

    @Override
    public void sendMailAsUser(String microsoftPrincipalName, String subject, String htmlContent, String... toRecipients) {
        log.info(MailConsolePrinter.format("as user " + microsoftPrincipalName, subject, htmlContent, toRecipients));
    }

    @Override
    public void sendMailAsUser(OAuth2AuthorizedClient authorizedClient, String subject, String htmlContent, String... toRecipients) {
        log.info(MailConsolePrinter.format("as user " + authorizedClient.getPrincipalName(), subject, htmlContent, toRecipients));
    }
}
