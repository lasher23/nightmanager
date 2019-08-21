package ch.uhc_yetis.nightmanager.service.impl;

import ch.uhc_yetis.nightmanager.service.TournamentService;
import ch.uhc_yetis.nightmanager.domain.Tournament;
import ch.uhc_yetis.nightmanager.repository.TournamentRepository;
import ch.uhc_yetis.nightmanager.service.dto.TournamentDTO;
import ch.uhc_yetis.nightmanager.service.mapper.TournamentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tournament}.
 */
@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {

    private final Logger log = LoggerFactory.getLogger(TournamentServiceImpl.class);

    private final TournamentRepository tournamentRepository;

    private final TournamentMapper tournamentMapper;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    /**
     * Save a tournament.
     *
     * @param tournamentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TournamentDTO save(TournamentDTO tournamentDTO) {
        log.debug("Request to save Tournament : {}", tournamentDTO);
        Tournament tournament = tournamentMapper.toEntity(tournamentDTO);
        tournament = tournamentRepository.save(tournament);
        return tournamentMapper.toDto(tournament);
    }

    /**
     * Get all the tournaments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TournamentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tournaments");
        return tournamentRepository.findAll(pageable)
            .map(tournamentMapper::toDto);
    }


    /**
     * Get one tournament by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TournamentDTO> findOne(Long id) {
        log.debug("Request to get Tournament : {}", id);
        return tournamentRepository.findById(id)
            .map(tournamentMapper::toDto);
    }

    /**
     * Delete the tournament by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tournament : {}", id);
        tournamentRepository.deleteById(id);
    }
}
