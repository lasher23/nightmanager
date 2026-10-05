package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.simulation.SimulationService;
import ch.uhc_yetis.nightmanager.application.simulation.dto.SimulationCategoryDto;
import ch.uhc_yetis.nightmanager.application.simulation.dto.SimulationCategorySpec;
import ch.uhc_yetis.nightmanager.application.simulation.dto.SimulationTournamentSummary;
import ch.uhc_yetis.nightmanager.domain.model.Tournament;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/tournaments")
    @PreAuthorize("hasAuthority('" + RoleConstants.SIMULATION_MANAGE + "')")
    public List<SimulationTournamentSummary> listTournaments() {
        return this.simulationService.listTournaments();
    }

    @PostMapping("/tournaments")
    @PreAuthorize("hasAuthority('" + RoleConstants.SIMULATION_MANAGE + "')")
    public Tournament createTournament(@RequestBody Map<String, String> body) {
        return this.simulationService.createTournament(body.get("name"));
    }

    @DeleteMapping("/tournaments/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.SIMULATION_MANAGE + "')")
    public void deleteTournament(@PathVariable long id) {
        this.simulationService.deleteTournament(id);
    }

    @GetMapping("/tournaments/{id}/categories")
    @PreAuthorize("hasAuthority('" + RoleConstants.SIMULATION_MANAGE + "')")
    public List<SimulationCategoryDto> getCategories(@PathVariable long id) {
        return this.simulationService.getCategories(id);
    }

    @PostMapping("/tournaments/{id}/categories")
    @PreAuthorize("hasAuthority('" + RoleConstants.SIMULATION_MANAGE + "')")
    public List<SimulationCategoryDto> addCategories(@PathVariable long id, @RequestBody List<SimulationCategorySpec> specs) {
        return this.simulationService.addCategories(id, specs);
    }
}
