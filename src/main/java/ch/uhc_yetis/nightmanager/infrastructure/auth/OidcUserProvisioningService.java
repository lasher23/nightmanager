package ch.uhc_yetis.nightmanager.infrastructure.auth;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Provisions a local {@link ApplicationUser} the first time a user authenticates via
 * an external OIDC provider (e.g. Microsoft Entra ID).
 *
 * New users are assigned the "USER" role which inherits the ANONYMOUS role's public-read
 * permissions. Administrators can later promote users to REFEREE, SHOT_MASTER, or ADMIN.
 */
@Service
public class OidcUserProvisioningService extends OidcUserService {

    private static final Logger log = LoggerFactory.getLogger(OidcUserProvisioningService.class);

    private final ApplicationUserRepository applicationUserRepository;

    public OidcUserProvisioningService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        String email = oidcUser.getEmail();
        if (email == null || email.isBlank()) {
            log.warn("OIDC login without email claim — skipping user provisioning");
            return oidcUser;
        }

        if (applicationUserRepository.findByEmail(email) == null) {
            ApplicationUser newUser = new ApplicationUser();
            newUser.setEmail(email);
            newUser.setUsername(oidcUser.getFullName() != null ? oidcUser.getFullName() : email);
            newUser.setEnabled(true);
            newUser.setRoles(Set.of("USER"));
            applicationUserRepository.save(newUser);
            log.info("Provisioned new user on first OIDC login: {}", email);
        }

        return oidcUser;
    }
}
