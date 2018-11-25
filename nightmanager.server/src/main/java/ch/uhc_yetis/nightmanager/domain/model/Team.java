package ch.uhc_yetis.nightmanager.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;
    @Column(unique = true)
    private String name;
    @Column(name = "flagPlaceholder")
    private boolean placeholder;
    @Column
    private int rank;


    public int getRank() {
        return this.rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return this.id == team.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public void setName(String name) {
        this.name = name;
    }
}
