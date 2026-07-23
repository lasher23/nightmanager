package ch.uhc_yetis.nightmanager.application.mail;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.Arrays;

/**
 * Sends system-initiated emails (e.g. login verification codes) via Microsoft Graph,
 * using the OAuth2 client-credentials ("app-only") flow with the {@code Mail.Send}
 * Application permission. Requires admin consent in Entra for that permission, and
 * that {@link #systemSender} refers to a real mailbox the app is allowed to send from.
 */
@Service
public class GraphSystemMailService {

    private static final Logger log = LoggerFactory.getLogger(GraphSystemMailService.class);
    private static final String GRAPH_SCOPE = "https://graph.microsoft.com/.default";

    private final RestClient tokenClient = RestClient.create();
    private final RestClient graphClient = RestClient.create("https://graph.microsoft.com/v1.0");

    private final String tenantId;
    private final String clientId;
    private final String clientSecret;
    private final String systemSender;

    private volatile CachedToken cachedToken;

    public GraphSystemMailService(
            @Value("${nightmanager.graph.tenant-id}") String tenantId,
            @Value("${spring.security.oauth2.client.registration.microsoft.client-id}") String clientId,
            @Value("${spring.security.oauth2.client.registration.microsoft.client-secret}") String clientSecret,
            @Value("${nightmanager.graph.system-sender}") String systemSender) {
        this.tenantId = tenantId;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.systemSender = systemSender;
    }

    /** Sends a plain-text email from the configured system mailbox. */
    public void sendMail(String subject, String content, String... toRecipients) {
        send(GraphMailMessage.of(subject, content, toRecipients), subject, toRecipients);
    }

    /** Sends an HTML email from the configured system mailbox. */
    public void sendHtmlMail(String subject, String htmlContent, String... toRecipients) {
        send(GraphMailMessage.ofHtml(subject, htmlContent, toRecipients), subject, toRecipients);
    }

    private void send(GraphMailMessage message, String subject, String... toRecipients) {
        String accessToken = getAccessToken();
        graphClient.post()
                .uri("/users/{sender}/sendMail", systemSender)
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(message)
                .retrieve()
                .toBodilessEntity();
        log.info("Sent system email '{}' to {}", subject, Arrays.toString(toRecipients));
    }

    private synchronized String getAccessToken() {
        CachedToken token = cachedToken;
        if (token != null && token.expiresAt().isAfter(Instant.now().plusSeconds(60))) {
            return token.value();
        }

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("scope", GRAPH_SCOPE);
        form.add("grant_type", "client_credentials");

        TokenResponse response = tokenClient.post()
                .uri("https://login.microsoftonline.com/{tenantId}/oauth2/v2.0/token", tenantId)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(TokenResponse.class);
        log.info("Obtained new Microsoft Graph app-only access token (expires in {} seconds)", response.expiresIn());

        if (response == null || response.accessToken() == null) {
            throw new IllegalStateException("Failed to obtain Microsoft Graph app-only access token");
        }

        CachedToken newToken = new CachedToken(response.accessToken(), Instant.now().plusSeconds(response.expiresIn()));
        cachedToken = newToken;
        return newToken.value();
    }

    private record CachedToken(String value, Instant expiresAt) {}

    private record TokenResponse(
            @JsonProperty("access_token") String accessToken,
            @JsonProperty("expires_in") long expiresIn) {}
}
