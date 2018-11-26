package ch.uhc_yetis.nightmanager.application.generation.yetiscup;

import ch.uhc_yetis.nightmanager.application.CategoryService;
import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.TeamComperator;
import ch.uhc_yetis.nightmanager.domain.model.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class YetisCupGenerator implements Generator {
    private static final Logger LOGGER = LoggerFactory.getLogger(YetisCupGenerator.class);
    private CategoryService categoryService;
    private TeamService teamService;
    private GameService gameService;
    private TeamComperator teamComperator;

    public YetisCupGenerator(CategoryService categoryService, TeamService teamService, GameService gameService, TeamComperator teamComperator) {
        this.categoryService = categoryService;
        this.teamService = teamService;
        this.gameService = gameService;
        this.teamComperator = teamComperator;
    }

    @Override
    @Transactional(rollbackFor = {GenerationException.class, IndexOutOfBoundsException.class})
    public void generate(Category category) {
        List<Category> subCategories = this.categoryService.findByParentCategory(category);
        List<TeamDto> teams = this.teamService.findByCategory(category).stream().sorted(this.teamComperator).collect(Collectors.toList());
        List<List<TeamDto>> teamSplitted = Lists.partition(teams, 10);
        for (int i = 0; ; i++) {
            if (i == 1000) {
                throw new GenerationException(null, "Please try again, maybe you get more luck,");
            }
            try {
                LOGGER.info("Try: " + (i + 1));
                this.generate(subCategories.get(1), teamSplitted.get(1));
                break;
            } catch (GenerationException ex) {
            }
        }
        for (int i = 0; ; i++) {
            if (i == 1000) {
                throw new GenerationException(null, "Please try again, maybe you get more luck,");
            }
            try {
                this.generate(subCategories.get(0), teamSplitted.get(0));
                break;
            } catch (GenerationException ex) {
                LOGGER.info("Try: " + i);
            }
        }
        subCategories.forEach(category1 -> {
            category1.setState(CategoryState.GROUP_PHASE);
            this.categoryService.save(category1);
        });
        category.setState(CategoryState.DISABLED);
    }

    private void generate(Category category, List<TeamDto> teamSplitted) {
        List<Team> teams = teamSplitted.stream().map(team -> this.teamService.findById(team.getId())).map(Optional::get).collect(Collectors.toList());
        teams.forEach(team -> this.updateTeam(category, team));
        List<Game> remainingGroupStageGames = this.gameService.getAllGamesByCategoryAndType(category, GameType.GROUP_STAGE).stream().sorted(Comparator.comparing(Game::getStartDate)).collect(Collectors.toList());
        List<Pair<Team, List<TeamGenerationData>>> teamsGeneration = teams.stream().map(team -> Pair.of(team, (List<TeamGenerationData>) new ArrayList<TeamGenerationData>())).collect(Collectors.toList());
        List<Team> alreadyGeneratedTeams = new ArrayList<>();
        this.generate(teams, remainingGroupStageGames, teamsGeneration, alreadyGeneratedTeams);
        alreadyGeneratedTeams.clear();
        this.generate(teams, remainingGroupStageGames, teamsGeneration, alreadyGeneratedTeams);
    }

    private void generate(List<Team> teams, List<Game> reamainingGroupstageGames, List<Pair<Team, List<TeamGenerationData>>> teamsGeneration, List<Team> alreadygeneratedTeams) {
        for (int i = 0; i < teams.size(); i++) {
            Team currentTeam = teams.get(i);
            if (alreadygeneratedTeams.contains(currentTeam)) {
                continue;
            }
            List<Hall> hallsAlreadyPlayed = teamsGeneration.get(i).getSecond().size() > 0 ? teamsGeneration.get(i).getSecond().get(0).getHallsAlreadyPlayed() : Arrays.asList();
            Game game = this.getGameThatHasntPlaydInHall(reamainingGroupstageGames, hallsAlreadyPlayed);
            reamainingGroupstageGames.remove(game);
            Pair<Team, List<TeamGenerationData>> teamAgainst = this.getRandomTeam(currentTeam, i, teamsGeneration, game, alreadygeneratedTeams);
            game.setTeamHome(currentTeam);
            game.setTeamGuest(teamAgainst.getFirst());
            game.setPlaceholder(false);
            List<TeamGenerationData> generationData = teamsGeneration.get(i).getSecond();
            generationData.add(new TeamGenerationData(Arrays.asList(game.getHall()), true, Arrays.asList(teamAgainst.getFirst())));
            teamAgainst.getSecond().add(new TeamGenerationData(Arrays.asList(game.getHall()), false, Arrays.asList(currentTeam)));
            this.gameService.save(game);
            alreadygeneratedTeams.add(currentTeam);
            alreadygeneratedTeams.add(teamAgainst.getFirst());
        }
    }

    private Game getGameThatHasntPlaydInHall(List<Game> reamainingGroupstageGames, List<Hall> hallsAlreadyPlayed) {
        for (int i = 0; i < reamainingGroupstageGames.size(); i++) {
            Game game = reamainingGroupstageGames.get(i);
            if (!hallsAlreadyPlayed.contains(game.getHall())) {
                return game;
            }
        }
        return reamainingGroupstageGames.get(0);
    }

    private Pair<Team, List<TeamGenerationData>> getRandomTeam(Team teamToMatchAgainst, int teamToMatchAgainstIndex, List<Pair<Team, List<TeamGenerationData>>> teams, Game game, List<Team> alreadygeneratedTeams) {
        List<Integer> alreadyTriedIndicies = new ArrayList<>();
        int teamIndex = this.getTeamIndex(teams.size(), alreadyTriedIndicies);
        alreadyTriedIndicies.add(teamIndex);
        int securityIndex = 0;
        while (!this.isTeamIndexValid(teamToMatchAgainst, teamToMatchAgainstIndex, teams, teamIndex, game, alreadygeneratedTeams)) {
            teamIndex = this.getTeamIndex(teams.size(), alreadyTriedIndicies);
            alreadyTriedIndicies.add(teamIndex);
            if (securityIndex > 10) {
                LOGGER.error("cant match team");
                // throw this exception because this is a possible endless loop if you are unlucky
                throw new GenerationException(null, "Please try again, maybe you get more luck,");
            }
            securityIndex++;
        }
        return teams.get(teamIndex);
    }

    private int getTeamIndex(int bound, List<Integer> alreadyTriedIndicies) {
        Random random = new Random();
        int randomInt = random.nextInt(bound);
        int securityIndex = 0;
        while (alreadyTriedIndicies.contains(randomInt)) {
            randomInt = random.nextInt(bound);
            securityIndex++;
            if (securityIndex > 1000) {
                return 5;
            }
        }
        return randomInt;
    }

    private boolean isTeamIndexValid(Team teamToMatchAgainst, int teamToMatchAgainstIndex, List<Pair<Team, List<TeamGenerationData>>> teams, int teamIndex, Game game, List<Team> alreadygeneratedTeams) {
        List<TeamGenerationData> data = teams.get(teamIndex).getSecond();
        return teams.get(teamIndex).getFirst() != teamToMatchAgainst
                && Math.abs(teamToMatchAgainstIndex - teamIndex) > 1
                && data.stream().allMatch(x -> {
            boolean didntPlayInThisHall = !x.getHallsAlreadyPlayed().contains(game.getHall());
            return !x.getPlayedAgainst().contains(teamToMatchAgainst) && didntPlayInThisHall;
        })
                && !alreadygeneratedTeams.contains(teams.get(teamIndex).getFirst());
    }

    private void updateTeam(Category category, Team team) {
        team.setCategory(category);
        this.teamService.save(team);
    }
}
