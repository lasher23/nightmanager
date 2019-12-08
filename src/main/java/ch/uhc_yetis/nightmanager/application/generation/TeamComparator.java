package ch.uhc_yetis.nightmanager.application.generation;

import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.GameType;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
public class TeamComparator implements Comparator<TeamDto> {
    private GameService gameService;
    private TeamService teamService;

    public TeamComparator(GameService gameService, TeamService teamService) {
        this.gameService = gameService;
        this.teamService = teamService;
    }

    @Override
    public int compare(TeamDto team1, TeamDto team2) {
        int result = Integer.compare(team1.getPoints(), team2.getPoints());
        if (result == 0) {
            result = this.getResultByGoalsDifference(team1, team2);
            if (result == 0) {
                result = this.getResultByGoalsShot(team1.getGoalsShot(), team2.getGoalsShot());
                if (result == 0) {
                    result = this.getResultByDirectGame(team1, team2);
                }
            }

        }
        return result * -1; // lowest value first!
    }

    private int getResultByDirectGame(TeamDto team1, TeamDto team2) {
        Optional<Game> game = this.gameService.findGameByTwoTeamsAndType(this.teamService.findById(team1.getId()).get(), this.teamService.findById(team2.getId()).get(), GameType.GROUP_STAGE);
        if (game.isPresent() && game.get().getTeamHome().getId() == team1.getId()) {
            return Integer.compare(game.get().getGoalsTeamHome(), game.get().getGoalsTeamGuest());
        } else if (game.isPresent() && game.get().getTeamGuest().getId() == team1.getId()) {
            return Integer.compare(game.get().getGoalsTeamGuest(), game.get().getGoalsTeamHome());
        } else {
            return 0;
        }
    }

    private int getResultByGoalsShot(int goalsShot, int goalsShot2) {
        return Integer.compare(goalsShot, goalsShot2);
    }

    private int getResultByGoalsDifference(TeamDto team1, TeamDto team2) {
        return Integer.compare(team1.getGoalsShot() - team1.getGoalsGotten(), team2.getGoalsShot() - team2.getGoalsGotten());
    }
}
