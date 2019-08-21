package ch.uhc_yetis.nightmanager.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import ch.uhc_yetis.nightmanager.domain.enumeration.GameState;

import ch.uhc_yetis.nightmanager.domain.enumeration.GameType;

/**
 * A Game.
 */
@Entity
@Table(name = "game")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "goals_home", nullable = false)
    private Integer goalsHome;

    @NotNull
    @Column(name = "goals_guest", nullable = false)
    private Integer goalsGuest;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private GameState state;

    @NotNull
    @Column(name = "placeholder", nullable = false)
    private Boolean placeholder;

    @NotNull
    @Column(name = "start", nullable = false)
    private ZonedDateTime start;

    @Column(name = "duration")
    private Long duration;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private GameType type;

    @ManyToOne
    @JsonIgnoreProperties("games")
    private Team home;

    @ManyToOne
    @JsonIgnoreProperties("games")
    private Team guest;

    @ManyToOne
    @JsonIgnoreProperties("games")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("games")
    private Hall hall;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGoalsHome() {
        return goalsHome;
    }

    public Game goalsHome(Integer goalsHome) {
        this.goalsHome = goalsHome;
        return this;
    }

    public void setGoalsHome(Integer goalsHome) {
        this.goalsHome = goalsHome;
    }

    public Integer getGoalsGuest() {
        return goalsGuest;
    }

    public Game goalsGuest(Integer goalsGuest) {
        this.goalsGuest = goalsGuest;
        return this;
    }

    public void setGoalsGuest(Integer goalsGuest) {
        this.goalsGuest = goalsGuest;
    }

    public GameState getState() {
        return state;
    }

    public Game state(GameState state) {
        this.state = state;
        return this;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Boolean isPlaceholder() {
        return placeholder;
    }

    public Game placeholder(Boolean placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public void setPlaceholder(Boolean placeholder) {
        this.placeholder = placeholder;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public Game start(ZonedDateTime start) {
        this.start = start;
        return this;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public Long getDuration() {
        return duration;
    }

    public Game duration(Long duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public GameType getType() {
        return type;
    }

    public Game type(GameType type) {
        this.type = type;
        return this;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public Team getHome() {
        return home;
    }

    public Game home(Team team) {
        this.home = team;
        return this;
    }

    public void setHome(Team team) {
        this.home = team;
    }

    public Team getGuest() {
        return guest;
    }

    public Game guest(Team team) {
        this.guest = team;
        return this;
    }

    public void setGuest(Team team) {
        this.guest = team;
    }

    public Category getCategory() {
        return category;
    }

    public Game category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Hall getHall() {
        return hall;
    }

    public Game hall(Hall hall) {
        this.hall = hall;
        return this;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        return id != null && id.equals(((Game) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Game{" +
            "id=" + getId() +
            ", goalsHome=" + getGoalsHome() +
            ", goalsGuest=" + getGoalsGuest() +
            ", state='" + getState() + "'" +
            ", placeholder='" + isPlaceholder() + "'" +
            ", start='" + getStart() + "'" +
            ", duration=" + getDuration() +
            ", type='" + getType() + "'" +
            "}";
    }
}
