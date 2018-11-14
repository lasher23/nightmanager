package ch.uhc_yetis.nightmanager.application.generation.standard;

import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StandardFinalGenerator implements Generator {
    @Autowired
    private GameService gameService;
    @Autowired
    private TeamService teamService;

    @Override
    public void generate(Category category) {
        List<Game> games = this.gameService.getAllGamesByCategoryAndType(category, GameType.SEMI_FINAL);
        if (games.stream().allMatch(game -> game.getState().equals(GameState.DONE))) {
            List<Game> semiFinals = this.gameService.getAllGamesByCategoryAndType(category, GameType.SEMI_FINAL);
            List<Game> finals = this.gameService.getAllGamesByCategoryAndType(category, GameType.FINAL).stream().sorted(Comparator.comparingLong(game -> game.getHall().getId())).collect(Collectors.toList());
            Game semi1 = semiFinals.get(0);
            Game semi2 = semiFinals.get(1);

            this.getWinner(semi1, category);
            semiFinals.get(1);
        } else {
            throw new GenerationException(category, "Es sind noch nicht alle Halbfinal Spiele abgeschlossen");
        }

    }

    private Team getWinner(Game game, Category category) {
        if (game.getGoalsTeamHome() > game.getGoalsTeamGuest()) {
            return game.getTeamHome();
        } else if (game.getGoalsTeamHome() < game.getGoalsTeamGuest()) {
            return game.getTeamGuest();
        } else {
            throw new GenerationException(category, "Darf keine Unentschieden in den Halbfinale geben");
        }
    }

    private Team getLooser(Game game, Category category) {
        if (game.getGoalsTeamHome() < game.getGoalsTeamGuest()) {
            return game.getTeamHome();
        } else if (game.getGoalsTeamHome() > game.getGoalsTeamGuest()) {
            return game.getTeamGuest();
        } else {
            throw new GenerationException(category, "Darf keine Unentschieden in den Halbfinale geben");
        }
    }
}
