package ch.uhc_yetis.nightmanager.service.impl;

import ch.uhc_yetis.nightmanager.service.GameService;
import ch.uhc_yetis.nightmanager.domain.Game;
import ch.uhc_yetis.nightmanager.repository.GameRepository;
import ch.uhc_yetis.nightmanager.service.dto.GameDTO;
import ch.uhc_yetis.nightmanager.service.mapper.GameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Game}.
 */
@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;

    public GameServiceImpl(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    /**
     * Save a game.
     *
     * @param gameDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GameDTO save(GameDTO gameDTO) {
        log.debug("Request to save Game : {}", gameDTO);
        Game game = gameMapper.toEntity(gameDTO);
        game = gameRepository.save(game);
        return gameMapper.toDto(game);
    }

    /**
     * Get all the games.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GameDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Games");
        return gameRepository.findAll(pageable)
            .map(gameMapper::toDto);
    }


    /**
     * Get one game by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GameDTO> findOne(Long id) {
        log.debug("Request to get Game : {}", id);
        return gameRepository.findById(id)
            .map(gameMapper::toDto);
    }

    /**
     * Delete the game by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Game : {}", id);
        gameRepository.deleteById(id);
    }
}
