package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "fk_parent_category")
    private Category parentCategory;
    @Enumerated(EnumType.ORDINAL)
    @Column
    private CategoryType type;
    @Enumerated(EnumType.ORDINAL)
    @Column
    private CategoryState state;
    @Column
    private String remark;

    @Column
    private String additionalSmsText;
    @Column
    private boolean showOnDisplay;


    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public CategoryType getType() {
        return this.type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public CategoryState getState() {
        return this.state;
    }

    public void setState(CategoryState state) {
        this.state = state;
    }

    public String getAdditionalSmsText() {
        return additionalSmsText;
    }

    public void setAdditionalSmsText(String additionalSmsText) {
        this.additionalSmsText = additionalSmsText;
    }

    public boolean isShowOnDisplay() {
        return showOnDisplay;
    }

    public void setShowOnDisplay(boolean showOnDisplay) {
        this.showOnDisplay = showOnDisplay;
    }
}
