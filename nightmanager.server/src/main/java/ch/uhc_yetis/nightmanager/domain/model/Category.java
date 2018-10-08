package ch.uhc_yetis.nightmanager.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "fk_parent_category")
    private Category parentCategory;
    @Column(name = "flagCroki")
    private boolean croki;
    @Enumerated(EnumType.ORDINAL)
    @Column
    private CategoryState state;
    @JoinColumn(nullable = false, name = "fk_tournament")
    @ManyToOne
    private Tournament tournament;

    public Tournament getTournament() {
        return this.tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
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

    public Category getParentCategory() {
        return this.parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public boolean isCroki() {
        return this.croki;
    }

    public void setCroki(boolean croki) {
        this.croki = croki;
    }

    public CategoryState getState() {
        return this.state;
    }

    public void setState(CategoryState state) {
        this.state = state;
    }
}
