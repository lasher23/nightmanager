package ch.uhc_yetis.nightmanager.application.mail;

/**
 * Abstraction over sending mail from the system/app mailbox (app-only permissions), so callers
 * don't depend directly on the real Microsoft Graph implementation and can be swapped for a
 * {@link MockSystemMailService} during local development.
 */
public interface SystemMailSender {

    /** Sends a plain-text email from the configured system mailbox. */
    void sendMail(String subject, String content, String... toRecipients);

    /** Sends an HTML email from the configured system mailbox. */
    void sendHtmlMail(String subject, String htmlContent, String... toRecipients);
}
