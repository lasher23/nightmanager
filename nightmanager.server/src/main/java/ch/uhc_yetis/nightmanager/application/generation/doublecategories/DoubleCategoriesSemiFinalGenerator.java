package ch.uhc_yetis.nightmanager.application.generation.doublecategories;

import ch.uhc_yetis.nightmanager.application.CategoryService;
import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.TeamComparator;
import ch.uhc_yetis.nightmanager.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoubleCategoriesSemiFinalGenerator implements Generator {

    private CategoryService categoryService;
    private TeamService teamService;
    private TeamComparator teamComparator;
    private GameService gameService;

    public DoubleCategoriesSemiFinalGenerator(CategoryService categoryService,
                                              TeamService teamService,
                                              TeamComparator teamComparator,
                                              GameService gameService) {
        this.categoryService = categoryService;
        this.teamService = teamService;
        this.teamComparator = teamComparator;
        this.gameService = gameService;
    }

    @Override
    public void generate(Category category) {
        List<Category> categories = this.categoryService.findByParentCategory(category);
        Category subCategory1 = categories.get(0);
        Category subCategory2 = categories.get(1);
        List<TeamDto> teamsSubCategory1 = this.teamService.findByCategory(subCategory1).stream().sorted(this.teamComparator).collect(Collectors.toList());
        List<TeamDto> teamsSubCategory2 = this.teamService.findByCategory(subCategory2).stream().sorted(this.teamComparator).collect(Collectors.toList());
        this.setRankingOfNotForPlayoffQualifiedTeams(teamsSubCategory1);
        this.setRankingOfNotForPlayoffQualifiedTeams(teamsSubCategory2);
        List<Game> games = this.gameService.getAllGamesByCategoryAndType(category, GameType.SEMI_FINAL).stream().sorted(Comparator.comparingLong(game -> game.getHall().getId())).collect(Collectors.toList());
        Game semiFinal1 = games.get(0);
        Game semiFinal2 = games.get(1);
        semiFinal1.setTeamHome(this.teamService.findById(teamsSubCategory1.get(0).getId()).orElseThrow(() -> new GenerationException(category, "")));
        semiFinal1.setTeamGuest(this.teamService.findById(teamsSubCategory2.get(1).getId()).orElseThrow(() -> new GenerationException(category, "")));
        semiFinal2.setTeamGuest(this.teamService.findById(teamsSubCategory1.get(1).getId()).orElseThrow(() -> new GenerationException(category, "")));
        semiFinal2.setTeamHome(this.teamService.findById(teamsSubCategory2.get(0).getId()).orElseThrow(() -> new GenerationException(category, "")));
        semiFinal1.setPlaceholder(false);
        semiFinal2.setPlaceholder(false);
        this.gameService.save(semiFinal1);
        this.gameService.save(semiFinal2);

        categories.stream().map(category1 -> this.teamService.findByCategory(category1))
                .flatMap(Collection::stream)
                .map(team -> this.teamService.findById(team.getId()).get())
                .forEach(team -> this.updateTeam(category, team));
        category.setState(CategoryState.SEMI_FINAL);
        this.categoryService.save(category);
    }

    private void setRankingOfNotForPlayoffQualifiedTeams(List<TeamDto> sortedTeams) {
        List<TeamDto> notQualifiedTeams = sortedTeams.subList(2, sortedTeams.size());
        for (int i = 0; i < notQualifiedTeams.size(); i++) {
            TeamDto team = notQualifiedTeams.get(i);
            Team teamToSave = this.teamService.findById(team.getId()).orElseThrow(() -> new RuntimeException());
            teamToSave.setRank(i + 5);
            this.teamService.save(teamToSave);
        }
    }

    private void updateTeam(Category category, Team team) {
        team.setCategory(category);
        this.teamService.save(team);
    }
}
