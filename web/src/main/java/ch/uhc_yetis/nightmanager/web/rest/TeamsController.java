package ch.uhc_yetis.nightmanager.web.rest;

import ch.uhc_yetis.nightmanager.service.api.teams.ITeamsService;
import ch.uhc_yetis.nightmanager.service.api.teams.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamsController {
    @Autowired
    ITeamsService teamsService;
    @GetMapping
    public List<TeamDto> getAllTeams() {
        return teamsService.getAll();
    }

}
