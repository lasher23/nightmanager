package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.OtpUser;

import java.time.Instant;
import java.util.Set;

public class OtpUserDto {
    private Long id;
    private String name;
    private String token;
    private Instant validUntil;
    private Instant createdAt;
    private Set<String> roles;
    private boolean expired;

    public OtpUserDto() {}

    public static OtpUserDto from(OtpUser u) {
        OtpUserDto dto = new OtpUserDto();
        dto.id        = u.getId();
        dto.name      = u.getName();
        dto.token     = u.getToken();
        dto.validUntil = u.getValidUntil();
        dto.createdAt  = u.getCreatedAt();
        dto.roles      = u.getRoles();
        dto.expired    = u.isExpired();
        return dto;
    }

    public Long getId()             { return id; }
    public String getName()         { return name; }
    public String getToken()        { return token; }
    public Instant getValidUntil()  { return validUntil; }
    public Instant getCreatedAt()   { return createdAt; }
    public Set<String> getRoles()   { return roles; }
    public boolean isExpired()      { return expired; }
}
