package ch.uhc_yetis.nightmanager.adapter.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularRoutesController {

    @RequestMapping(value = {"/", "/referee/**", "/display/**", "/admin/**", "/v2/**", "/oidc/**"})
    public String redirectToHtml() {
        return "forward:/index.html";
    }
}
