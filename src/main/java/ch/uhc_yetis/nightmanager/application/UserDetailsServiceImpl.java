package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final PermissionService permissionService;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository,
                                  PermissionService permissionService) {
        this.applicationUserRepository = applicationUserRepository;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser applicationUser = this.applicationUserRepository.findByEmail(email);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }
        // Resolve the flat permission set from all assigned roles (recursively)
        var permissions = permissionService.resolvePermissions(applicationUser.getRoles());
        return new User(
                applicationUser.getEmail(),
                "{noop}test", // TODO: temporary dummy password for testing
                applicationUser.isEnabled(),
                true, true, true,
                permissions.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }
}

