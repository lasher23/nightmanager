package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Role;
import ch.uhc_yetis.nightmanager.domain.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleManagementService {

    private final RoleRepository roleRepository;

    public RoleManagementService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleDto::from)
                .toList();
    }

    public RoleDto findById(Long id) {
        return roleRepository.findById(id)
                .map(RoleDto::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found: " + id));
    }

    public RoleDto create(RoleRequest request) {
        if (roleRepository.findByName(request.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exists: " + request.getName());
        }
        Role role = new Role();
        role.setName(request.getName());
        role.setPermissions(request.getPermissions() != null ? request.getPermissions() : Set.of());
        role.setChildRoles(resolveChildRoles(request.getChildRoleNames()));
        return RoleDto.from(roleRepository.save(role));
    }

    public RoleDto update(Long id, RoleRequest request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found: " + id));

        if (request.getName() != null) {
            role.setName(request.getName());
        }
        if (request.getPermissions() != null) {
            role.setPermissions(request.getPermissions());
        }
        if (request.getChildRoleNames() != null) {
            role.setChildRoles(resolveChildRoles(request.getChildRoleNames()));
        }

        return RoleDto.from(roleRepository.save(role));
    }

    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found: " + id);
        }
        roleRepository.deleteById(id);
    }

    private Set<Role> resolveChildRoles(Set<String> names) {
        if (names == null || names.isEmpty()) {
            return Set.of();
        }
        Set<Role> children = new HashSet<>();
        for (String name : names) {
            Role child = roleRepository.findByName(name)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Child role not found: " + name));
            children.add(child);
        }
        return children;
    }
}
