package ch.uhc_yetis.nightmanager.application.generation.standard;

import ch.uhc_yetis.nightmanager.application.CategoryService;
import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.TeamComperator;
import ch.uhc_yetis.nightmanager.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StandardSemiFinalGenerator implements Generator {
    private GameService gameService;
    private TeamService teamService;
    private TeamComperator teamComperator;
    private CategoryService categoryService;

    public StandardSemiFinalGenerator(GameService gameService, TeamService teamService, TeamComperator teamComperator, CategoryService categoryService) {
        this.gameService = gameService;
        this.teamService = teamService;
        this.teamComperator = teamComperator;
        this.categoryService = categoryService;
    }

    @Override
    public void generate(Category category) {
        List<Game> games = this.gameService.getAllGamesByCategoryAndType(category, GameType.GROUP_STAGE);
        if (games.stream().allMatch(game -> game.getState().equals(GameState.DONE))) {
            List<TeamDto> sortedTeams = this.getSortedTeams(category);
            List<Game> sortedSemifinals = this.getSortedSemiFinals(category);
            Game firstSemi = sortedSemifinals.get(0);
            firstSemi.setTeamHome(this.teamService.findById(sortedTeams.get(0).getId()).get());
            firstSemi.setTeamGuest(this.teamService.findById(sortedTeams.get(3).getId()).get());
            this.gameService.save(firstSemi);
            Game secondSemi = sortedSemifinals.get(1);
            secondSemi.setTeamHome(this.teamService.findById(sortedTeams.get(1).getId()).get());
            secondSemi.setTeamGuest(this.teamService.findById(sortedTeams.get(2).getId()).get());
            this.gameService.save(secondSemi);
            this.setRankingOfNotForPlayoffQualifiedTeams(sortedTeams);
            category.setState(CategoryState.SEMI_FINAL);
            this.categoryService.save(category);
        } else {
            throw new GenerationException(category, "Alle Gruppenspiele müssen abgeschlossen sein");
        }
    }

    private void setRankingOfNotForPlayoffQualifiedTeams(List<TeamDto> sortedTeams) {
        List<TeamDto> notQualifiedTeams = sortedTeams.subList(4, sortedTeams.size() - 1);
        for (int i = 0; i < notQualifiedTeams.size(); i++) {
            TeamDto team = notQualifiedTeams.get(i);
            Team teamToSave = this.teamService.findById(team.getId()).orElseThrow(() -> new RuntimeException());
            teamToSave.setRank(i + 5);
            this.teamService.save(teamToSave);
        }
    }

    private List<TeamDto> getSortedTeams(Category category) {
        return this.teamService.findByCategory(category).stream().sorted(this.teamComperator).collect(Collectors.toList());
    }

    private List<Game> getSortedSemiFinals(Category category) {
        List<Game> semiFinalGames = this.gameService.getGamesByGameTypeAndCategory(GameType.SEMI_FINAL, category);
        return semiFinalGames.stream().sorted(Comparator.comparingLong(game -> game.getHall().getId())).collect(Collectors.toList());
    }
}
