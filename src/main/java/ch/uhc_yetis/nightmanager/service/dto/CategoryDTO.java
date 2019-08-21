package ch.uhc_yetis.nightmanager.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import ch.uhc_yetis.nightmanager.domain.enumeration.CategoryType;
import ch.uhc_yetis.nightmanager.domain.enumeration.CategoryState;

/**
 * A DTO for the {@link ch.uhc_yetis.nightmanager.domain.Category} entity.
 */
public class CategoryDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private CategoryType type;

    @NotNull
    private CategoryState state;

    private String remark;


    private Long tournamentId;

    private Long parentCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CategoryState getState() {
        return state;
    }

    public void setState(CategoryState state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long categoryId) {
        this.parentCategoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryDTO categoryDTO = (CategoryDTO) o;
        if (categoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", state='" + getState() + "'" +
            ", remark='" + getRemark() + "'" +
            ", tournament=" + getTournamentId() +
            ", parentCategory=" + getParentCategoryId() +
            "}";
    }
}
