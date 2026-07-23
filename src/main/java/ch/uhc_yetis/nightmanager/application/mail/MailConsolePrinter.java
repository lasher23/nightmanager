package ch.uhc_yetis.nightmanager.application.mail;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Shared helper for the mock mail senders: prints a short, human-readable summary of an
 * email to the console/log instead of actually sending it, so local development doesn't
 * require real Microsoft Graph credentials.
 */
final class MailConsolePrinter {

    private static final Pattern TAG_PATTERN = Pattern.compile("<[^>]+>");
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
    private static final int PREVIEW_MAX_LENGTH = 300;

    private MailConsolePrinter() {
    }

    static String format(String kind, String subject, String content, String... toRecipients) {
        return """

                ===== MOCK MAIL (%s) =====
                To:      %s
                Subject: %s
                Preview: %s
                ==========================""".formatted(
                kind, Arrays.toString(toRecipients), subject, plainTextPreview(content));
    }

    private static String plainTextPreview(String content) {
        if (content == null || content.isBlank()) {
            return "(empty)";
        }
        String plainText = WHITESPACE_PATTERN.matcher(TAG_PATTERN.matcher(content).replaceAll(" ")).replaceAll(" ").trim();
        if (plainText.length() > PREVIEW_MAX_LENGTH) {
            return plainText.substring(0, PREVIEW_MAX_LENGTH) + "…";
        }
        return plainText;
    }
}
