package ch.uhc_yetis.nightmanager.service.mapper;

import ch.uhc_yetis.nightmanager.domain.*;
import ch.uhc_yetis.nightmanager.service.dto.CategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {TournamentMapper.class})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

    @Mapping(source = "tournament.id", target = "tournamentId")
    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    CategoryDTO toDto(Category category);

    @Mapping(source = "tournamentId", target = "tournament")
    @Mapping(source = "parentCategoryId", target = "parentCategory")
    @Mapping(target = "games", ignore = true)
    @Mapping(target = "removeGame", ignore = true)
    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "removeTeam", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
