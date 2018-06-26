package ch.uhc_yetis.nightmanager.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private long name;
    @ManyToOne
    @JoinColumn(name = "fk_parent_category")
    private Category parentCategory;
    @Column(name = "flagCroki")
    private boolean croki;
    @Enumerated(EnumType.ORDINAL)
    @Column
    private CategoryState state;

    public long getId() {
        return id;
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public boolean isCroki() {
        return croki;
    }

    public void setCroki(boolean croki) {
        this.croki = croki;
    }

    public CategoryState getState() {
        return state;
    }

    public void setState(CategoryState state) {
        this.state = state;
    }
}
