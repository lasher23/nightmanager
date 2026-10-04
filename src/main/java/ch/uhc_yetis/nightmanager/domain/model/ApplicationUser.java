package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String username;

    /**
     * The OIDC "sub" (principal name) from the user's last Microsoft Entra login.
     * Used to look up their delegated Graph access token (e.g. to send mail as them)
     * outside of their login session, via {@code OAuth2AuthorizedClientManager}.
     */
    @Column(name = "microsoft_principal_name")
    private String microsoftPrincipalName;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role_assignment", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim().toLowerCase();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMicrosoftPrincipalName() {
        return microsoftPrincipalName;
    }

    public void setMicrosoftPrincipalName(String microsoftPrincipalName) {
        this.microsoftPrincipalName = microsoftPrincipalName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
