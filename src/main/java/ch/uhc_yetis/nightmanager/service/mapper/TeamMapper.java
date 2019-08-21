package ch.uhc_yetis.nightmanager.service.mapper;

import ch.uhc_yetis.nightmanager.domain.*;
import ch.uhc_yetis.nightmanager.service.dto.TeamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Team} and its DTO {@link TeamDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {

    @Mapping(source = "category.id", target = "categoryId")
    TeamDTO toDto(Team team);

    @Mapping(source = "categoryId", target = "category")
    Team toEntity(TeamDTO teamDTO);

    default Team fromId(Long id) {
        if (id == null) {
            return null;
        }
        Team team = new Team();
        team.setId(id);
        return team;
    }
}
