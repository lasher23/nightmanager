package ch.uhc_yetis.nightmanager.application.generation.standard;

import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.TeamComperator;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.GameState;
import ch.uhc_yetis.nightmanager.domain.model.GameType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StandardSemiFinalGenerator implements Generator {
    @Autowired
    private GameService gameService;
    @Autowired
    private TeamService teamService;

    @Override
    public void generate(Category category) {
        List<Game> games = this.gameService.getAllGamesByCategoryAndType(category, GameType.SEMI_FINAL);
        if (games.stream().allMatch(game -> game.getState().equals(GameState.DONE))) {
            List<TeamDto> sortedTeams = this.getSortedTeams(category);
            List<Game> sortedSemifinals = this.getSortedSemiFinals(category);
            Game firstSemi = sortedSemifinals.get(0);
            firstSemi.setTeamHome(this.teamService.findById(sortedTeams.get(0).getId()).get());
            firstSemi.setTeamGuest(this.teamService.findById(sortedTeams.get(3).getId()).get());
            this.gameService.save(firstSemi);
            Game secondSemi = sortedSemifinals.get(1);
            secondSemi.setTeamHome(this.teamService.findById(sortedTeams.get(1).getId()).get());
            secondSemi.setTeamHome(this.teamService.findById(sortedTeams.get(2).getId()).get());
            this.gameService.save(secondSemi);
        } else {
            throw new GenerationException(category, "Alle Gruppenspiele müssen abgeschlossen sein");
        }

    }

    private List<TeamDto> getSortedTeams(Category category) {
        return this.teamService.findByCategory(category).stream().sorted(new TeamComperator()).collect(Collectors.toList());
    }

    private List<Game> getSortedSemiFinals(Category category) {
        List<Game> semiFinalGames = this.gameService.getGamesByGameTypeAndCategory(GameType.SEMI_FINAL, category);
        return semiFinalGames.stream().sorted(Comparator.comparingLong(game -> game.getHall().getId())).collect(Collectors.toList());
    }
}
