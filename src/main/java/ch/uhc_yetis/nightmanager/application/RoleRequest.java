package ch.uhc_yetis.nightmanager.application;

import java.util.Set;

public class RoleRequest {
    private String name;
    private Set<String> permissions;
    private Set<String> childRoleNames;

    public String getName()                  { return name; }
    public void setName(String name)         { this.name = name; }

    public Set<String> getPermissions()      { return permissions; }
    public void setPermissions(Set<String> permissions) { this.permissions = permissions; }

    public Set<String> getChildRoleNames()   { return childRoleNames; }
    public void setChildRoleNames(Set<String> childRoleNames) { this.childRoleNames = childRoleNames; }
}
