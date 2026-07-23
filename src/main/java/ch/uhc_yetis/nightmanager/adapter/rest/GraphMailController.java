package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.mail.UserMailSender;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Lets a user who logged in via Microsoft Entra send an email as themselves through
 * Microsoft Graph. Only works for users authenticated via the "microsoft" OIDC login
 * (their session must hold an authorized client with the {@code Mail.Send} scope) —
 * users logged in via email-code/OTP/password do not have a linked Microsoft account.
 */
@RestController
@RequestMapping("api/mail")
public class GraphMailController {

    private final UserMailSender graphUserMailService;

    public GraphMailController(UserMailSender graphUserMailService) {
        this.graphUserMailService = graphUserMailService;
    }

    public record SendMailRequest(String subject, String content, String[] to) {}

    @PostMapping("/send-as-me")
    public void sendAsMe(@RequestBody SendMailRequest request,
                          @RegisteredOAuth2AuthorizedClient("microsoft") OAuth2AuthorizedClient authorizedClient) {
        graphUserMailService.sendMailAsUser(authorizedClient, request.subject(), request.content(), request.to());
    }
}
