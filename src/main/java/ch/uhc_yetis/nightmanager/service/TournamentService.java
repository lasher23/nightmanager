package ch.uhc_yetis.nightmanager.service;

import ch.uhc_yetis.nightmanager.service.dto.TournamentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ch.uhc_yetis.nightmanager.domain.Tournament}.
 */
public interface TournamentService {

    /**
     * Save a tournament.
     *
     * @param tournamentDTO the entity to save.
     * @return the persisted entity.
     */
    TournamentDTO save(TournamentDTO tournamentDTO);

    /**
     * Get all the tournaments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TournamentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tournament.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TournamentDTO> findOne(Long id);

    /**
     * Delete the "id" tournament.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
