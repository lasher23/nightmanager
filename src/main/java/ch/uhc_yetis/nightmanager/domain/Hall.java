package ch.uhc_yetis.nightmanager.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Hall.
 */
@Entity
@Table(name = "hall")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Hall implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "hall")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Game> games = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Hall name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getGames() {
        return games;
    }

    public Hall games(Set<Game> games) {
        this.games = games;
        return this;
    }

    public Hall addGame(Game game) {
        this.games.add(game);
        game.setHall(this);
        return this;
    }

    public Hall removeGame(Game game) {
        this.games.remove(game);
        game.setHall(null);
        return this;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hall)) {
            return false;
        }
        return id != null && id.equals(((Hall) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Hall{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
