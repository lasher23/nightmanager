package ch.uhc_yetis.nightmanager.application.mail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSON payload matching the Microsoft Graph {@code sendMail} action body.
 * Shared between the app-only (system) and delegated (user) Graph mail services.
 */
record GraphMailMessage(Message message, boolean saveToSentItems) {

    static GraphMailMessage of(String subject, String content, String... toRecipients) {
        return of(subject, content, "Text", toRecipients);
    }

    static GraphMailMessage ofHtml(String subject, String htmlContent, String... toRecipients) {
        return of(subject, htmlContent, "HTML", toRecipients);
    }

    private static GraphMailMessage of(String subject, String content, String contentType, String... toRecipients) {
        List<Recipient> recipients = Arrays.stream(toRecipients)
                .map(address -> new Recipient(new EmailAddress(address)))
                .collect(Collectors.toList());
        return new GraphMailMessage(new Message(subject, new Body(contentType, content), recipients), false);
    }

    record Message(String subject, Body body, List<Recipient> toRecipients) {}

    record Body(String contentType, String content) {}

    record Recipient(EmailAddress emailAddress) {}

    record EmailAddress(String address) {}
}
