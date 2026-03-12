package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Role;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleDto {
    private Long id;
    private String name;
    private Set<String> permissions;
    private Set<String> childRoles;

    public RoleDto() {}

    public static RoleDto from(Role role) {
        RoleDto dto = new RoleDto();
        dto.id          = role.getId();
        dto.name        = role.getName();
        dto.permissions = role.getPermissions();
        dto.childRoles  = role.getChildRoles().stream()
                              .map(Role::getName)
                              .collect(Collectors.toSet());
        return dto;
    }

    public Long getId()                { return id; }
    public String getName()            { return name; }
    public Set<String> getPermissions(){ return permissions; }
    public Set<String> getChildRoles() { return childRoles; }
}
