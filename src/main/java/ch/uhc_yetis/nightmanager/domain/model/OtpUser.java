package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * An OTP user is a named, time-limited login token.
 * It does not require an email address – instead a UUID token is generated
 * and can be distributed as a QR-code or manual text input.
 */
@Entity
@Table(name = "otp_user")
public class OtpUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Human-readable label, must be unique. */
    @Column(unique = true, nullable = false)
    private String name;

    /** The secret token presented at login (UUID). */
    @Column(unique = true, nullable = false)
    private String token;

    /** Login is only accepted before this point in time. */
    @Column(name = "valid_until", nullable = false)
    private Instant validUntil;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "otp_user_role_assignment", joinColumns = @JoinColumn(name = "otp_user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    // ---- getters / setters ----

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Instant getValidUntil() { return validUntil; }
    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }

    public Instant getCreatedAt() { return createdAt; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public boolean isExpired() {
        return Instant.now().isAfter(validUntil);
    }
}
