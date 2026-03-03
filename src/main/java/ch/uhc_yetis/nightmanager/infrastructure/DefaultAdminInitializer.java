package ch.uhc_yetis.nightmanager.infrastructure;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Creates a default admin user on first startup if no users exist.
 */
@Component
public class DefaultAdminInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DefaultAdminInitializer.class);

    private final ApplicationUserRepository applicationUserRepository;

    @Value("${nightmanager.auth.default-admin-email}")
    private String defaultAdminEmail;

    public DefaultAdminInitializer(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
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
