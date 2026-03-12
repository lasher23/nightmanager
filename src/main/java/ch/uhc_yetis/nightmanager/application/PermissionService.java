package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Role;
import ch.uhc_yetis.nightmanager.domain.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Resolves a set of role names into a flat set of permission strings by recursively
 * walking the role hierarchy. Cycle-safe via a visited set.
 */
@Service
public class PermissionService {

    private final RoleRepository roleRepository;

    public PermissionService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Returns the full flat permission set for a collection of role names.
     */
    public Set<String> resolvePermissions(Set<String> roleNames) {
        Set<String> permissions = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (String roleName : roleNames) {
            roleRepository.findByName(roleName)
                    .ifPresent(role -> collectPermissions(role, permissions, visited));
        }
        return permissions;
    }

    /**
     * Returns the full flat permission set for a single role name.
     */
    public Set<String> resolvePermissionsForRole(String roleName) {
        return resolvePermissions(Set.of(roleName));
    }

    private void collectPermissions(Role role, Set<String> permissions, Set<String> visited) {
        if (!visited.add(role.getName())) {
            return; // already visited — prevents infinite loops in cyclic graphs
        }
        permissions.addAll(role.getPermissions());
        for (Role child : role.getChildRoles()) {
            collectPermissions(child, permissions, visited);
        }
    }
}
