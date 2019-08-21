package ch.uhc_yetis.nightmanager.web.rest;

import ch.uhc_yetis.nightmanager.service.TournamentService;
import ch.uhc_yetis.nightmanager.web.rest.errors.BadRequestAlertException;
import ch.uhc_yetis.nightmanager.service.dto.TournamentDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ch.uhc_yetis.nightmanager.domain.Tournament}.
 */
@RestController
@RequestMapping("/api")
public class TournamentResource {

    private final Logger log = LoggerFactory.getLogger(TournamentResource.class);

    private static final String ENTITY_NAME = "tournament";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TournamentService tournamentService;

    public TournamentResource(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    /**
     * {@code POST  /tournaments} : Create a new tournament.
     *
     * @param tournamentDTO the tournamentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tournamentDTO, or with status {@code 400 (Bad Request)} if the tournament has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tournaments")
    public ResponseEntity<TournamentDTO> createTournament(@Valid @RequestBody TournamentDTO tournamentDTO) throws URISyntaxException {
        log.debug("REST request to save Tournament : {}", tournamentDTO);
        if (tournamentDTO.getId() != null) {
            throw new BadRequestAlertException("A new tournament cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TournamentDTO result = tournamentService.save(tournamentDTO);
        return ResponseEntity.created(new URI("/api/tournaments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tournaments} : Updates an existing tournament.
     *
     * @param tournamentDTO the tournamentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tournamentDTO,
     * or with status {@code 400 (Bad Request)} if the tournamentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tournamentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tournaments")
    public ResponseEntity<TournamentDTO> updateTournament(@Valid @RequestBody TournamentDTO tournamentDTO) throws URISyntaxException {
        log.debug("REST request to update Tournament : {}", tournamentDTO);
        if (tournamentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TournamentDTO result = tournamentService.save(tournamentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tournamentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tournaments} : get all the tournaments.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tournaments in body.
     */
    @GetMapping("/tournaments")
    public ResponseEntity<List<TournamentDTO>> getAllTournaments(Pageable pageable) {
        log.debug("REST request to get a page of Tournaments");
        Page<TournamentDTO> page = tournamentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tournaments/:id} : get the "id" tournament.
     *
     * @param id the id of the tournamentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tournamentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tournaments/{id}")
    public ResponseEntity<TournamentDTO> getTournament(@PathVariable Long id) {
        log.debug("REST request to get Tournament : {}", id);
        Optional<TournamentDTO> tournamentDTO = tournamentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tournamentDTO);
    }

    /**
     * {@code DELETE  /tournaments/:id} : delete the "id" tournament.
     *
     * @param id the id of the tournamentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tournaments/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        log.debug("REST request to delete Tournament : {}", id);
        tournamentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
