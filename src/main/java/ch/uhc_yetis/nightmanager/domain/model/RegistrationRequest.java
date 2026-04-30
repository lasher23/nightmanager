package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "registration_requests")
public class RegistrationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String contactEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationRequestStatus status = RegistrationRequestStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_registration_group", nullable = false)
    private RegistrationGroup registrationGroup;

    @ElementCollection
    @CollectionTable(name = "registration_request_birthdays",
            joinColumns = @JoinColumn(name = "fk_registration_request"))
    @Column(name = "birthday")
    private List<LocalDate> memberBirthdays = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public RegistrationRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationRequestStatus status) {
        this.status = status;
    }

    public RegistrationGroup getRegistrationGroup() {
        return registrationGroup;
    }

    public void setRegistrationGroup(RegistrationGroup registrationGroup) {
        this.registrationGroup = registrationGroup;
    }

    public List<LocalDate> getMemberBirthdays() {
        return memberBirthdays;
    }

    public void setMemberBirthdays(List<LocalDate> memberBirthdays) {
        this.memberBirthdays = memberBirthdays;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
