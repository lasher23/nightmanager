package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getAll() {
        return teamService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable long id) {
        Optional<Team> team = teamService.findById(id);
        if (!team.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(team.get());
    }

    @PostMapping
    public Team saveNewTeam(@RequestBody Team team) {
        return teamService.createNewTeam(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> saveItem(@PathVariable long id, @RequestBody Team team) {
        if (team.getId() != id && team.getId() != 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamService.save(team));
    }
}
