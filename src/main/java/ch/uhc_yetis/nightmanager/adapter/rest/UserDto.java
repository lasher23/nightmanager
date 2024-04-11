package ch.uhc_yetis.nightmanager.adapter.rest;

import java.util.List;

public class UserDto {
    private String username;
    private List<String> roles;

    public void setRoles(List<String> role) {
        this.roles = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
