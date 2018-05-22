package ch.uhc_yetis.nightmanager.service.impl.teams;

import ch.uhc_yetis.nightmanager.repository.api.ITeamRepository;
import ch.uhc_yetis.nightmanager.service.api.teams.ITeamsService;
import ch.uhc_yetis.nightmanager.service.api.teams.TeamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService implements ITeamsService{
    @Autowired
    ITeamRepository teamRepository;
    @Override
    public List<TeamDto> getAll() {
        return teamRepository.findAll().stream().map(x -> TeamDto.withId(x.getId()).build()).collect(Collectors.toList());
    }
}
