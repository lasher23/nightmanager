package ch.uhc_yetis.nightmanager.service.mapper;

import ch.uhc_yetis.nightmanager.domain.*;
import ch.uhc_yetis.nightmanager.service.dto.GameDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Game} and its DTO {@link GameDTO}.
 */
@Mapper(componentModel = "spring", uses = {TeamMapper.class, CategoryMapper.class, HallMapper.class})
public interface GameMapper extends EntityMapper<GameDTO, Game> {

    @Mapping(source = "home.id", target = "homeId")
    @Mapping(source = "guest.id", target = "guestId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "hall.id", target = "hallId")
    GameDTO toDto(Game game);

    @Mapping(source = "homeId", target = "home")
    @Mapping(source = "guestId", target = "guest")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "hallId", target = "hall")
    Game toEntity(GameDTO gameDTO);

    default Game fromId(Long id) {
        if (id == null) {
            return null;
        }
        Game game = new Game();
        game.setId(id);
        return game;
    }
}
