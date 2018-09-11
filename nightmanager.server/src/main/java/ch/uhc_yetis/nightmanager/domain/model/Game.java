package ch.uhc_yetis.nightmanager.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @GeneratedValue
    @Id
    private long id;
    @ManyToOne
    @JoinColumn(name = "fk_team_home")
    private Team teamHome;
    @ManyToOne
    @JoinColumn(name = "fk_team_guest")
    private Team teamGuest;
    @Column(name = "goals_team_guest")
    private int goalsTeamGuest;
    @Column(name = "goals_team_home")
    private int goalsTeamHome;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private GameState state;
    @Column(name = "flagPlaceholder")
    private boolean placeholder;
    @ManyToOne
    @JoinColumn(name = "fk_hall")
    private Hall hall;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;

    public Hall getHall() {
        return this.hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPlaceholder() {
        return this.placeholder;
    }

    public void setPlaceholder(boolean placeholder) {
        this.placeholder = placeholder;
    }

    public long getId() {
        return this.id;
    }

    public Team getTeamHome() {
        return this.teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamGuest() {
        return this.teamGuest;
    }

    public void setTeamGuest(Team teamGuest) {
        this.teamGuest = teamGuest;
    }

    public int getGoalsTeamGuest() {
        return this.goalsTeamGuest;
    }

    public void setGoalsTeamGuest(int goalsTeamGuest) {
        this.goalsTeamGuest = goalsTeamGuest;
    }

    public int getGoalsTeamHome() {
        return this.goalsTeamHome;
    }

    public void setGoalsTeamHome(int goalsTeamHome) {
        this.goalsTeamHome = goalsTeamHome;
    }

    public GameState getState() {
        return this.state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
