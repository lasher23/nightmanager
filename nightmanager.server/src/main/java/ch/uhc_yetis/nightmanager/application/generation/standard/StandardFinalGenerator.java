package ch.uhc_yetis.nightmanager.application.generation.standard;

import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StandardFinalGenerator implements Generator {
    private GameService gameService;

    public StandardFinalGenerator(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void generate(Category category) {
        List<Game> games = this.gameService.getAllGamesByCategoryAndType(category, GameType.SEMI_FINAL);
        if (games.stream().allMatch(game -> game.getState().equals(GameState.DONE))) {
            List<Game> semiFinals = this.gameService.getAllGamesByCategoryAndType(category, GameType.SEMI_FINAL);
            List<Game> finals = this.gameService.getAllGamesByCategoryAndType(category, GameType.FINAL).stream().sorted(Comparator.comparingLong(game -> game.getHall().getId())).collect(Collectors.toList());
            Game bigFinal = finals.get(0);
            Game semi1 = semiFinals.get(0);
            Game semi2 = semiFinals.get(1);
            Team winner1 = this.getWinner(semi1, category);
            Team winner2 = this.getWinner(semi2, category);
            bigFinal.setTeamHome(winner1);
            bigFinal.setTeamGuest(winner2);
            bigFinal.setPlaceholder(false);
            bigFinal.setState(GameState.OPEN);
            this.gameService.save(bigFinal);

            Game littleFinal = finals.get(1);
            Team looser1 = this.getLooser(semi1, category);
            Team looser2 = this.getLooser(semi2, category);
            littleFinal.setTeamHome(looser1);
            littleFinal.setTeamGuest(looser2);
            littleFinal.setPlaceholder(false);
            littleFinal.setState(GameState.OPEN);
            this.gameService.save(littleFinal);
        } else {
            throw new GenerationException(category, "Es sind noch nicht alle Halbfinal Spiele abgeschlossen");
        }

    }

    private Team getWinner(Game game, Category category) {
        return this.gameService.getWinner(game).orElseThrow(() -> new GenerationException(category, "In einem Halbfinale braucht es immer ein Gewinner"));
    }

    private Team getLooser(Game game, Category category) {
        return this.gameService.getLooser(game).orElseThrow(() -> new GenerationException(category, "In einem Halbfinale braucht es immer ein Gewinner"));
    }
}
