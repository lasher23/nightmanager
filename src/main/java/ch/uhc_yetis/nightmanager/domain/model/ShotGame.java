package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "shot_games")
public class ShotGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_team")
    private Team team;

    @Column(name = "shots")
    private int shots;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShotGame shotGame = (ShotGame) o;
        return id == shotGame.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShotGame{" +
                "id=" + id +
                ", team=" + (team != null ? team.getId() : null) +
                ", shots=" + shots +
                '}';
    }
}
