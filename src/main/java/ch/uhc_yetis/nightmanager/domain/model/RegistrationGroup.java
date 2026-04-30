package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registration_groups")
public class RegistrationGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "requires_age", nullable = false)
    private boolean requiresAge = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tournament", nullable = false)
    private Tournament tournament;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequiresAge() {
        return requiresAge;
    }

    public void setRequiresAge(boolean requiresAge) {
        this.requiresAge = requiresAge;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
