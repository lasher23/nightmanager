package ch.uhc_yetis.nightmanager.application.generation.splitcategories;

import ch.uhc_yetis.nightmanager.application.CategoryService;
import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.TeamComperator;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.GameType;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SplitCategoriesSemiFinalGenerator implements Generator {

    private CategoryService categoryService;
    private TeamService teamService;
    private TeamComperator teamComperator;
    private GameService gameService;

    public SplitCategoriesSemiFinalGenerator(CategoryService categoryService,
                                             TeamService teamService,
                                             TeamComperator teamComperator,
                                             GameService gameService) {
        this.categoryService = categoryService;
        this.teamService = teamService;
        this.teamComperator = teamComperator;
        this.gameService = gameService;
    }

    @Override
    public void generate(Category category) {
        List<Category> categories = this.categoryService.findByParentCategory(category);
        Category subCategory1 = categories.get(0);
        Category subCategory2 = categories.get(1);
        List<TeamDto> teamsSubCategory1 = this.teamService.findByCategory(subCategory1).stream().sorted(teamComperator).collect(Collectors.toList());
        List<TeamDto> teamsSubCategory2 = this.teamService.findByCategory(subCategory2).stream().sorted(teamComperator).collect(Collectors.toList());
        List<Game> games = gameService.getAllGamesByCategoryAndType(category, GameType.SEMI_FINAL);
        Game semiFinal1 = games.get(0);
        Game semiFinal2 = games.get(1);
        semiFinal1.setTeamHome(this.teamService.findById(teamsSubCategory1.get(0).getId()).orElseThrow(() -> new GenerationException(category, "")));
        semiFinal1.setTeamGuest(this.teamService.findById(teamsSubCategory2.get(1).getId()).orElseThrow(() -> new GenerationException(category, "")));
        semiFinal2.setTeamHome(this.teamService.findById(teamsSubCategory1.get(1).getId()).orElseThrow(() -> new GenerationException(category, "")));
        semiFinal2.setTeamGuest(this.teamService.findById(teamsSubCategory2.get(0).getId()).orElseThrow(() -> new GenerationException(category, "")));
        this.gameService.save(semiFinal1);
        this.gameService.save(semiFinal2);

        categories.stream().map(category1 -> this.teamService.findByCategory(category1))
                .flatMap(Collection::stream)
                .map(team -> teamService.findById(team.getId()).get())
                .forEach(team -> updateTeam(category, team));
    }

    private void updateTeam(Category category, Team team) {
        team.setCategory(category);
        teamService.save(team);
    }
}
