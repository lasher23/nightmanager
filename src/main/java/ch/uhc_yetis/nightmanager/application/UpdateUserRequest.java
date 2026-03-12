package ch.uhc_yetis.nightmanager.application;

import java.util.Set;

public class UpdateUserRequest {
    private String username;
    private Boolean enabled;
    private Set<String> roles;

    public String getUsername()   { return username; }
    public void setUsername(String username) { this.username = username; }

    public Boolean getEnabled()   { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
