package ch.uhc_yetis.nightmanager.service.mapper;

import ch.uhc_yetis.nightmanager.domain.*;
import ch.uhc_yetis.nightmanager.service.dto.TournamentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tournament} and its DTO {@link TournamentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TournamentMapper extends EntityMapper<TournamentDTO, Tournament> {


    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "removeCategory", ignore = true)
    Tournament toEntity(TournamentDTO tournamentDTO);

    default Tournament fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tournament tournament = new Tournament();
        tournament.setId(id);
        return tournament;
    }
}
