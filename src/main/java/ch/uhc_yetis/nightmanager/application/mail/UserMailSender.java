package ch.uhc_yetis.nightmanager.application.mail;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

/**
 * Abstraction over sending mail "as" a logged-in user (delegated permissions), so callers
 * don't depend directly on the real Microsoft Graph implementation and can be swapped for a
 * {@link MockUserMailService} during local development.
 */
public interface UserMailSender {

    /**
     * Sends mail as the user identified by their Microsoft OIDC principal name (subject).
     */
    void sendMailAsUser(String microsoftPrincipalName, String subject, String htmlContent, String... toRecipients);

    /**
     * Sends mail as the user behind the given already-authorized Microsoft client.
     */
    void sendMailAsUser(OAuth2AuthorizedClient authorizedClient, String subject, String htmlContent, String... toRecipients);
}
