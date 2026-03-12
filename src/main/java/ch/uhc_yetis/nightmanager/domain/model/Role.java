package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A database-backed role.
 * Roles are recursive: a role can include child roles (compound roles).
 * The effective permissions of a role = its direct permissions + all permissions of all child roles (transitively).
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Direct permissions granted by this role, e.g. "game:update", "category:create".
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "permission")
    private Set<String> permissions = new HashSet<>();

    /**
     * Child roles whose permissions are also included when this role is resolved.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_child_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "child_role_id")
    )
    private Set<Role> childRoles = new HashSet<>();

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<String> getPermissions() { return permissions; }
    public void setPermissions(Set<String> permissions) { this.permissions = permissions; }

    public Set<Role> getChildRoles() { return childRoles; }
    public void setChildRoles(Set<Role> childRoles) { this.childRoles = childRoles; }
}
