package ch.uhc_yetis.nightmanager.domain.model;

import javax.persistence.*;

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

    public void setName(String name) {
        this.name = name;
    }
}
