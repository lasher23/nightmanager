package ch.uhc_yetis.nightmanager.application.gamegeneration.dto;

import ch.uhc_yetis.nightmanager.domain.model.CategoryType;

/**
 * A category referenced by a proposal. Either an already existing category
 * ({@link #existingCategoryId} set) selected by the user, or a "virtual"
 * follow-up category (e.g. the Yetis Cup winner/loser split) that will only
 * be created in the database once the proposal is committed.
 */
public class ProposedCategoryDto {
    private String ref;
    private String name;
    private CategoryType type;
    private Long existingCategoryId;
    private Long parentCategoryId;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public Long getExistingCategoryId() {
        return existingCategoryId;
    }

    public void setExistingCategoryId(Long existingCategoryId) {
        this.existingCategoryId = existingCategoryId;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
