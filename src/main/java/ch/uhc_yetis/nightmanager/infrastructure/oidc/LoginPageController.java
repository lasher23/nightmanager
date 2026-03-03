package ch.uhc_yetis.nightmanager.infrastructure.oidc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Forwards /login to the static login.html page.
 * The login page handles the passwordless email + OTP login for the OIDC authorization flow.
 */
@Controller
public class LoginPageController {

    @GetMapping("/login")
    public String loginPage() {
        return "forward:/login.html";
    }
}
