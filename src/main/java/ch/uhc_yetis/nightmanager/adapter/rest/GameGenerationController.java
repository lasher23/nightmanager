package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.gamegeneration.GameGenerationService;
import ch.uhc_yetis.nightmanager.application.gamegeneration.dto.GameGenerationProposalDto;
import ch.uhc_yetis.nightmanager.application.gamegeneration.dto.ProposalSummaryDto;
import ch.uhc_yetis.nightmanager.application.gamegeneration.dto.ProposeRequest;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game-generation")
public class GameGenerationController {

    private final GameGenerationService gameGenerationService;

    public GameGenerationController(GameGenerationService gameGenerationService) {
        this.gameGenerationService = gameGenerationService;
    }

    @GetMapping("/categories")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_GENERATION_LIST + "')")
    public List<Category> getEligibleCategories(@RequestParam long tournamentId) {
        return this.gameGenerationService.getEligibleCategories(tournamentId);
    }

    @GetMapping("/proposals")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_GENERATION_LIST + "')")
    public List<ProposalSummaryDto> listProposals(@RequestParam long tournamentId) {
        return this.gameGenerationService.listProposals(tournamentId);
    }

    @GetMapping("/proposals/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_GENERATION_LIST + "')")
    public GameGenerationProposalDto getProposal(@PathVariable long id) {
        return this.gameGenerationService.getProposal(id);
    }

    @PostMapping("/propose")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_GENERATION_CREATE + "')")
    public GameGenerationProposalDto propose(@RequestParam long tournamentId, @RequestBody ProposeRequest request) {
        return this.gameGenerationService.propose(tournamentId, request);
    }

    @PutMapping("/proposals/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_GENERATION_CREATE + "')")
    public GameGenerationProposalDto updateProposal(@PathVariable long id, @RequestBody GameGenerationProposalDto dto) {
        return this.gameGenerationService.updateProposal(id, dto);
    }

    @DeleteMapping("/proposals/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_GENERATION_DELETE + "')")
    public void deleteProposal(@PathVariable long id) {
        this.gameGenerationService.deleteProposal(id);
    }

    @PostMapping("/proposals/{id}/commit")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_GENERATION_CREATE + "')")
    public List<Category> commit(@PathVariable long id) {
        return this.gameGenerationService.commit(id);
    }
}
