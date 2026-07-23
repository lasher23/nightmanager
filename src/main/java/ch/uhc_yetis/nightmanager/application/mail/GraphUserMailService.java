package ch.uhc_yetis.nightmanager.application.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;

/**
 * Sends emails on behalf of the currently logged-in user via Microsoft Graph, using the
 * delegated access token obtained during their Microsoft Entra OIDC login (requires the
 * {@code Mail.Send} delegated scope to have been consented to at login time).
 */
@Service
public class GraphUserMailService {

    private static final Logger log = LoggerFactory.getLogger(GraphUserMailService.class);
    private static final String REGISTRATION_ID = "microsoft";

    private final RestClient graphClient = RestClient.create("https://graph.microsoft.com/v1.0");
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public GraphUserMailService(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    /**
     * Sends mail as the user identified by their Microsoft OIDC principal name (subject),
     * resolving/refreshing their delegated access token via the {@link OAuth2AuthorizedClientManager}.
     * Works even outside of that user's own HTTP request/session (e.g. triggered by an admin action).
     *
     * @param microsoftPrincipalName the OIDC "sub" stored on the {@code ApplicationUser}
     *                                who should appear as the sender
     */
    public void sendMailAsUser(String microsoftPrincipalName, String subject, String htmlContent, String... toRecipients) {
        if (microsoftPrincipalName == null || microsoftPrincipalName.isBlank()) {
            throw new IllegalStateException("Kein Microsoft-Konto mit diesem Benutzer verknüpft — bitte einmal über Microsoft anmelden.");
        }

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId(REGISTRATION_ID)
                .principal(microsoftPrincipalName)
                .build();

        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
        if (authorizedClient == null) {
            throw new IllegalStateException(
                    "Kein gültiger Microsoft-Zugriffstoken für diesen Benutzer gefunden — bitte erneut über Microsoft anmelden.");
        }

        sendMailAsUser(authorizedClient, subject, htmlContent, toRecipients);
    }

    /**
     * @param authorizedClient the caller's authorized Microsoft client, e.g. obtained via
     *                          {@code @RegisteredOAuth2AuthorizedClient("microsoft")} on a
     *                          controller method. Its access token must include the
     *                          {@code Mail.Send} scope.
     */
    public void sendMailAsUser(OAuth2AuthorizedClient authorizedClient, String subject, String htmlContent, String... toRecipients) {
        if (authorizedClient == null) {
            throw new IllegalStateException("No Microsoft account linked for this user — cannot send mail on their behalf");
        }

        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        graphClient.post()
                .uri("/me/sendMail")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(GraphMailMessage.ofHtml(subject, htmlContent, toRecipients))
                .retrieve()
                .toBodilessEntity();
        log.info("Sent user email '{}' to {}", subject, Arrays.toString(toRecipients));
    }
}

