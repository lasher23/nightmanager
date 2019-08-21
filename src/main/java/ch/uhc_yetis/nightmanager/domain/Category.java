package ch.uhc_yetis.nightmanager.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ch.uhc_yetis.nightmanager.domain.enumeration.CategoryType;

import ch.uhc_yetis.nightmanager.domain.enumeration.CategoryState;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CategoryType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CategoryState state;

    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties("categories")
    private Tournament tournament;

    @ManyToOne
    @JsonIgnoreProperties("categories")
    private Category parentCategory;

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Game> games = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Team> teams = new HashSet<>();

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

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public Category type(CategoryType type) {
        this.type = type;
        return this;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public CategoryState getState() {
        return state;
    }

    public Category state(CategoryState state) {
        this.state = state;
        return this;
    }

    public void setState(CategoryState state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public Category remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Category tournament(Tournament tournament) {
        this.tournament = tournament;
        return this;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public Category parentCategory(Category category) {
        this.parentCategory = category;
        return this;
    }

    public void setParentCategory(Category category) {
        this.parentCategory = category;
    }

    public Set<Game> getGames() {
        return games;
    }

    public Category games(Set<Game> games) {
        this.games = games;
        return this;
    }

    public Category addGame(Game game) {
        this.games.add(game);
        game.setCategory(this);
        return this;
    }

    public Category removeGame(Game game) {
        this.games.remove(game);
        game.setCategory(null);
        return this;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public Category teams(Set<Team> teams) {
        this.teams = teams;
        return this;
    }

    public Category addTeam(Team team) {
        this.teams.add(team);
        team.setCategory(this);
        return this;
    }

    public Category removeTeam(Team team) {
        this.teams.remove(team);
        team.setCategory(null);
        return this;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", state='" + getState() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
