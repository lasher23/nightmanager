package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.RegistrationRequestService;
import ch.uhc_yetis.nightmanager.domain.model.RegistrationRequest;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/registration-requests")
public class RegistrationRequestController {

    private final RegistrationRequestService registrationRequestService;

    public RegistrationRequestController(RegistrationRequestService registrationRequestService) {
        this.registrationRequestService = registrationRequestService;
    }

    /** Authenticated users can submit a registration request while the tournament is open. */
    @PostMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_REQUEST_CREATE + "')")
    public RegistrationRequest create(@RequestParam Long groupId,
                                      @RequestBody RegistrationRequest request,
                                      Authentication authentication) {
        return registrationRequestService.create(groupId, request, extractEmail(authentication));
    }

    private String extractEmail(Authentication authentication) {
        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            return oidcUser.getEmail();
        }
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String email = jwt.getClaimAsString("email");
            if (email != null && !email.isBlank()) return email;
            // Microsoft Entra ID uses "preferred_username" for the UPN/email
            String upn = jwt.getClaimAsString("preferred_username");
            if (upn != null && !upn.isBlank()) return upn;
        }
        // For code/password/OTP login the username is the email
        return authentication.getName();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_REQUEST_LIST + "')")
    public List<RegistrationRequest> getByGroup(@RequestParam(required = false) Long groupId,
                                                @RequestParam(required = false) Long tournamentId) {
        if (groupId != null) {
            return registrationRequestService.findByGroupId(groupId);
        }
        if (tournamentId != null) {
            return registrationRequestService.findByTournamentId(tournamentId);
        }
        return List.of();
    }

    @PatchMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_REQUEST_APPROVE + "')")
    public RegistrationRequest approve(@PathVariable Long id) {
        return registrationRequestService.approve(id);
    }

    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_REQUEST_REJECT + "')")
    public RegistrationRequest reject(@PathVariable Long id) {
        return registrationRequestService.reject(id);
    }
}
