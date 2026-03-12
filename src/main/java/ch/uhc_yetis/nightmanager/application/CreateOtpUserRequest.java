package ch.uhc_yetis.nightmanager.application;

import java.time.Instant;
import java.util.Set;

public class CreateOtpUserRequest {
    private String name;
    private Instant validUntil;
    private Set<String> roles;

    public String getName()             { return name; }
    public void setName(String name)    { this.name = name; }

    public Instant getValidUntil()              { return validUntil; }
    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }

    public Set<String> getRoles()               { return roles; }
    public void setRoles(Set<String> roles)     { this.roles = roles; }
}
