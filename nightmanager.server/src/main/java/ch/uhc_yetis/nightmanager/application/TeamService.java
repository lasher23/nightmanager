package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import ch.uhc_yetis.nightmanager.domain.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GameService gameService;

    public Team createNewTeam(Team team) {
        team.setId(0);
        return this.teamRepository.save(team);
    }

    public List<TeamDto> findAll() {
        return this.teamRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Optional<Team> findById(long id) {
        return this.teamRepository.findById(id);
    }

    public Team save(Team team) {
        return this.teamRepository.save(team);
    }

    public List<TeamDto> findByCategory(Long categoryId) {
        return this.teamRepository.findByCategory(this.categoryService.findById(categoryId)).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private TeamDto mapToDto(Team team) {
        return TeamDto//
                .withId(team.getId())
                .withName(team.getName())
                .withCategory(team.getCategory())
                .withPlaceholder(team.isPlaceholder())
                .withGoalsShot(this.getGoalsShot(team))
                .withGoalsGotten(this.getGoalsGotten(team))
                .withPoints(this.getPoints(team))
                .withRank(team.getRank())
                .withPaid(team.isPaid())
                .build();
    }

    private int getPoints(Team team) {
        return this.gameService.getAllGroupstageGamesFromTeam(team).stream().map(game -> this.getPointsForTeam(team, game)).mapToInt(value -> value).sum();
    }

    private int getPointsForTeam(Team team, Game game) {
        if (game.getGoalsTeamGuest() > game.getGoalsTeamHome()) {
            if (game.getTeamGuest().getId() == team.getId()) {
                return 3;
            } else {
                return 0;
            }
        } else if (game.getGoalsTeamGuest() < game.getGoalsTeamHome()) {
            if (game.getTeamHome().getId() == team.getId()) {
                return 3;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    private int getGoalsGotten(Team team) {
        List<Game> allGamesFromTeam = this.gameService.getAllFromTeam(team);
        return allGamesFromTeam.stream().map(x -> {
            if (x.getTeamHome().getId() == team.getId()) {
                return x.getGoalsTeamGuest();
            } else if (x.getTeamGuest().getId() == team.getId()) {
                return x.getGoalsTeamHome();
            }
            return 0;
        }).mapToInt(Integer::intValue).sum();
    }

    private int getGoalsShot(Team team) {
        List<Game> allGamesFromTeam = this.gameService.getAllFromTeam(team);
        return allGamesFromTeam.stream().map(x -> {
            if (x.getTeamHome().getId() == team.getId()) {
                return x.getGoalsTeamHome();
            } else if (x.getTeamGuest().getId() == team.getId()) {
                return x.getGoalsTeamGuest();
            }
            return 0;
        }).mapToInt(Integer::intValue).sum();
    }

    public List<TeamDto> findByCategory(Category category) {
        return this.teamRepository.findByCategory(category).stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
