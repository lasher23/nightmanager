package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.TournamentService;
import ch.uhc_yetis.nightmanager.domain.model.Tournament;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tournaments")
public class TournamentController {
    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.TOURNAMENT_LIST + "')")
    public List<Tournament> getAll() {
        return this.tournamentService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.TOURNAMENT_GET + "')")
    public Tournament getById(@PathVariable long id) {
        return this.tournamentService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.TOURNAMENT_CREATE + "')")
    public Tournament create(@RequestBody Tournament tournament) {
        return this.tournamentService.create(tournament);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.TOURNAMENT_UPDATE + "')")
    public Tournament update(@RequestBody Tournament tournament) {
        return this.tournamentService.save(tournament);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.TOURNAMENT_DELETE + "')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        this.tournamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
