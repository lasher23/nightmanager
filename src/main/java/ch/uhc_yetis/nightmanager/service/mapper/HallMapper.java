package ch.uhc_yetis.nightmanager.service.mapper;

import ch.uhc_yetis.nightmanager.domain.*;
import ch.uhc_yetis.nightmanager.service.dto.HallDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Hall} and its DTO {@link HallDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HallMapper extends EntityMapper<HallDTO, Hall> {


    @Mapping(target = "games", ignore = true)
    @Mapping(target = "removeGame", ignore = true)
    Hall toEntity(HallDTO hallDTO);

    default Hall fromId(Long id) {
        if (id == null) {
            return null;
        }
        Hall hall = new Hall();
        hall.setId(id);
        return hall;
    }
}
