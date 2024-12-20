package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDto> getAll(@RequestParam(required = false) Long categoryId) {
        if (categoryId == null) {
            return this.teamService.findAll();
        } else {
            return this.teamService.findByCategory(categoryId);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable long id) {
        Optional<Team> team = this.teamService.findById(id);
        if (!team.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(team.get());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.ADMIN + "')")
    public Team saveNewTeam(@RequestBody Team team) {
        return this.teamService.createNewTeam(team);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.ADMIN + "')")
    public ResponseEntity<Team> saveItem(@RequestBody Team team) {
        return ResponseEntity.ok(this.teamService.save(team));
    }
}
