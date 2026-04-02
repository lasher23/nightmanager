package ch.uhc_yetis.nightmanager.infrastructure;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.model.Role;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import ch.uhc_yetis.nightmanager.domain.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Seeds the role hierarchy and creates a default admin user on first startup.
 *
 * Role hierarchy:
 *   ANONYMOUS  — public read permissions
 *   REFEREE    — game:update + chat:create  (inherits ANONYMOUS)
 *   SHOT_MASTER — shot:update               (inherits ANONYMOUS)
 *   ADMIN      — write/manage permissions   (inherits REFEREE + SHOT_MASTER)
 */
@Component
public class DefaultAdminInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DefaultAdminInitializer.class);

    private final ApplicationUserRepository applicationUserRepository;
    private final RoleRepository roleRepository;

    @Value("${nightmanager.auth.default-admin-email}")
    private String defaultAdminEmail;

    public DefaultAdminInitializer(ApplicationUserRepository applicationUserRepository,
                                   RoleRepository roleRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        seedRoles();
        seedDefaultAdmin();
    }

    private void seedRoles() {
        // ANONYMOUS — what unauthenticated users can do
        Role anonymous = findOrCreate("ANONYMOUS");
        anonymous.setPermissions(Set.of(
                RoleConstants.CATEGORY_LIST, RoleConstants.CATEGORY_GET,
                RoleConstants.GAME_LIST,     RoleConstants.GAME_GET,
                RoleConstants.HALL_LIST,     RoleConstants.HALL_GET,
                RoleConstants.TEAM_LIST,     RoleConstants.TEAM_GET,
                RoleConstants.SHOT_LIST,
                RoleConstants.NOTIFICATION_LIST,
                RoleConstants.CHAT_LIST,
                RoleConstants.TOURNAMENT_LIST, RoleConstants.TOURNAMENT_GET
        ));
        anonymous.setChildRoles(Set.of());
        roleRepository.save(anonymous);

        // USER — base role for every authenticated user; inherits ANONYMOUS
        Role user = findOrCreate("USER");
        user.setPermissions(Set.of());
        user.setChildRoles(Set.of(anonymous));
        roleRepository.save(user);

        // REFEREE — can update games and write to chat; inherits USER
        Role referee = findOrCreate("REFEREE");
        referee.setPermissions(Set.of(RoleConstants.GAME_UPDATE, RoleConstants.CHAT_CREATE));
        referee.setChildRoles(Set.of(user));
        roleRepository.save(referee);

        // SHOT_MASTER — can increment shots; inherits USER
        Role shotMaster = findOrCreate("SHOT_MASTER");
        shotMaster.setPermissions(Set.of(RoleConstants.SHOT_UPDATE));
        shotMaster.setChildRoles(Set.of(user));
        roleRepository.save(shotMaster);

        // ADMIN — full control; inherits REFEREE + SHOT_MASTER (which bring in ANONYMOUS transitively)
        Role admin = findOrCreate("ADMIN");
        admin.setPermissions(Set.of(
                RoleConstants.CATEGORY_CREATE,
                RoleConstants.GENERATION_CREATE,
                RoleConstants.NOTIFICATION_CREATE,
                RoleConstants.TEAM_CREATE,      RoleConstants.TEAM_UPDATE,
                RoleConstants.GAME_MANAGE,      RoleConstants.GAME_NOTIFY,
                RoleConstants.USER_LIST,        RoleConstants.USER_GET,        RoleConstants.USER_UPDATE,
                RoleConstants.ROLE_LIST,        RoleConstants.ROLE_GET,
                RoleConstants.ROLE_CREATE,      RoleConstants.ROLE_UPDATE,     RoleConstants.ROLE_DELETE,
                RoleConstants.OTP_USER_LIST,    RoleConstants.OTP_USER_GET,
                RoleConstants.OTP_USER_CREATE,  RoleConstants.OTP_USER_DELETE,
                RoleConstants.TOURNAMENT_LIST,  RoleConstants.TOURNAMENT_GET,
                RoleConstants.TOURNAMENT_CREATE, RoleConstants.TOURNAMENT_UPDATE, RoleConstants.TOURNAMENT_DELETE
        ));
        admin.setChildRoles(Set.of(referee, shotMaster));
        roleRepository.save(admin);

        log.info("Role hierarchy seeded.");
    }

    private Role findOrCreate(String name) {
        return roleRepository.findByName(name).orElseGet(() -> {
            Role r = new Role();
            r.setName(name);
            return r;
        });
    }

    private void seedDefaultAdmin() {
        if (applicationUserRepository.count() == 0) {
            ApplicationUser admin = new ApplicationUser();
            admin.setEmail(defaultAdminEmail);
            admin.setUsername("Admin");
            admin.setRoles(Set.of("ADMIN"));
            admin.setEnabled(true);
            applicationUserRepository.save(admin);
            log.info("Created default admin user with email: {}", defaultAdminEmail);
        }
    }
}

