package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.ApplicationUser;

import java.time.Instant;
import java.util.Set;

public class UserDto {
    private Long id;
    private String email;
    private String username;
    private boolean enabled;
    private Instant createdAt;
    private Set<String> roles;

    public UserDto() {}

    public static UserDto from(ApplicationUser user) {
        UserDto dto = new UserDto();
        dto.id        = user.getId();
        dto.email     = user.getEmail();
        dto.username  = user.getUsername();
        dto.enabled   = user.isEnabled();
        dto.createdAt = user.getCreatedAt();
        dto.roles     = user.getRoles();
        return dto;
    }

    public Long getId()           { return id; }
    public String getEmail()      { return email; }
    public String getUsername()   { return username; }
    public boolean isEnabled()    { return enabled; }
    public Instant getCreatedAt() { return createdAt; }
    public Set<String> getRoles() { return roles; }
}
