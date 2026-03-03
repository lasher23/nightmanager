package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.PasswordlessAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/passwordless")
public class PasswordlessAuthController {

    private final PasswordlessAuthService passwordlessAuthService;

    public PasswordlessAuthController(PasswordlessAuthService passwordlessAuthService) {
        this.passwordlessAuthService = passwordlessAuthService;
    }

    @PostMapping("/send-code")
    public ResponseEntity<Map<String, String>> sendCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email is required"));
        }
        passwordlessAuthService.sendVerificationCode(email.trim().toLowerCase());
        return ResponseEntity.ok(Map.of("message", "If the email is registered, a code has been sent."));
    }
}
