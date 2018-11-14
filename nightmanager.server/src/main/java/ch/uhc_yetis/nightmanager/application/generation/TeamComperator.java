package ch.uhc_yetis.nightmanager.application.generation;

import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.GameType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.Optional;

public class TeamComperator implements Comparator<TeamDto> {
    @Autowired
    private GameService gameService;

    @Override
    public int compare(TeamDto team1, TeamDto team2) {

        int result = Integer.compare(team1.getPoints(), team2.getPoints());
        if (result == 0) {
            Optional<Game> game = this.gameService.findGameByTwoTeamsAndType(team1, team2, GameType.GROUP_STAGE);
            if (game.isPresent() && game.get().getTeamHome().getId() == team1.getId()) {
                return Integer.compare(game.get().getGoalsTeamHome(), game.get().getGoalsTeamGuest());
            } else if (game.isPresent() && game.get().getTeamGuest().getId() == team1.getId()) {
                return Integer.compare(game.get().getGoalsTeamGuest(), game.get().getGoalsTeamHome());
            } else {
                return 0;
            }
        } else {
            return result;
        }
    }
}
