package ch.uhc_yetis.nightmanager.application.generation.yetiscup;

import ch.uhc_yetis.nightmanager.application.CategoryService;
import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.TeamComparator;
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
    private TeamComparator teamComparator;

    public YetisCupGenerator(CategoryService categoryService, TeamService teamService, GameService gameService, TeamComparator teamComparator) {
        this.categoryService = categoryService;
        this.teamService = teamService;
        this.gameService = gameService;
        this.teamComparator = teamComparator;
    }

    @Override
    @Transactional(rollbackFor = {GenerationException.class, IndexOutOfBoundsException.class})
    public void generate(Category category) {
        List<Category> subCategories = this.categoryService.findByParentCategory(category);
        List<TeamDto> teams = this.teamService.findByCategory(category).stream().sorted(this.teamComparator).collect(Collectors.toList());
        List<List<TeamDto>> teamSplitted = Lists.partition(teams, 10);
        for (int i = 0; ; i++) {
            if (i == 50_000) {
                throw new GenerationException(null, "Please try again, maybe you get more luck,");
            }
            try {
                LOGGER.info("Try: " + (i + 1));
                // Looser
                this.generate(subCategories.get(1), teamSplitted.get(1), List.of(2, 1, 2));
                break;
            } catch (GenerationException ex) {
            }
        }
        for (int i = 0; ; i++) {
            if (i == 50_000) {
                throw new GenerationException(null, "Please try again, maybe you get more luck,");
            }
            try {
                // Winner
                this.generate(subCategories.get(0), teamSplitted.get(0), List.of(2, 1, 2));
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

    private void generate(Category category, List<TeamDto> teamSplitted, List<Integer> hallsToGenerate) {
        List<Team> teams = teamSplitted.stream().map(team -> this.teamService.findById(team.getId())).map(Optional::get).collect(Collectors.toList());
        teams.forEach(team -> this.updateTeam(category, team));
        List<Game> remainingGroupStageGames = this.gameService.getAllGamesByCategoryAndType(category, GameType.GROUP_STAGE).stream().sorted(Comparator.comparing(Game::getStartDate)).collect(Collectors.toList());
        List<Pair<Team, List<TeamGenerationData>>> teamsGeneration = teams.stream().map(team -> {
            ArrayList<TeamGenerationData> teamGenerationData = new ArrayList<>();
            List<Game> games = this.gameService.getAllFromTeam(team);
            List<Team> teamsPlayedAgainst = games.stream().map(game -> this.getTeamAgainst(game, team)).collect(Collectors.toList());
            teamGenerationData.add(new TeamGenerationData(new ArrayList<>(), teamsPlayedAgainst));
            return Pair.of(team, (List<TeamGenerationData>) teamGenerationData);
        }).collect(Collectors.toList());
        List<Team> alreadyGeneratedTeams = new ArrayList<>();
        this.generate(teams, remainingGroupStageGames, teamsGeneration, alreadyGeneratedTeams, hallsToGenerate.get(0));
        alreadyGeneratedTeams.clear();
        this.generate(teams, remainingGroupStageGames, teamsGeneration, alreadyGeneratedTeams, hallsToGenerate.get(1));
        alreadyGeneratedTeams.clear();
        this.generate(teams, remainingGroupStageGames, teamsGeneration, alreadyGeneratedTeams, hallsToGenerate.get(2));
    }

    private Team getTeamAgainst(Game game, Team team) {
        if (game.getTeamHome().getId() == team.getId()) {
            return game.getTeamGuest();
        } else {
            return game.getTeamHome();
        }
    }

    private void generate(List<Team> teams, List<Game> reamainingGroupstageGames, List<Pair<Team, List<TeamGenerationData>>> teamsGeneration, List<Team> alreadygeneratedTeams, Integer hallId) {
        for (int i = 0; i < teams.size(); i++) {
            Team currentTeam = teams.get(i);
            if (alreadygeneratedTeams.contains(currentTeam)) {
                continue;
            }
            Game game = this.getGamePlayedInHall(reamainingGroupstageGames, hallId);
            reamainingGroupstageGames.remove(game);
            Pair<Team, List<TeamGenerationData>> teamAgainst = this.getRandomTeam(currentTeam, i, teamsGeneration, game, alreadygeneratedTeams);
            game.setTeamHome(currentTeam);
            game.setTeamGuest(teamAgainst.getFirst());
            game.setPlaceholder(false);
            List<TeamGenerationData> generationData = teamsGeneration.get(i).getSecond();
            generationData.add(new TeamGenerationData(Arrays.asList(game.getHall()), Arrays.asList(teamAgainst.getFirst())));
            teamAgainst.getSecond().add(new TeamGenerationData(Arrays.asList(game.getHall()), Arrays.asList(currentTeam)));
            this.gameService.save(game);
            alreadygeneratedTeams.add(currentTeam);
            alreadygeneratedTeams.add(teamAgainst.getFirst());
        }
    }

    private Game getGamePlayedInHall(List<Game> reamainingGroupstageGames, Integer hall) {
        for (int i = 0; i < reamainingGroupstageGames.size(); i++) {
            Game game = reamainingGroupstageGames.get(i);
            if (game.getHall().getId() == hall.longValue()) {
                return game;
            }
        }
        return reamainingGroupstageGames.get(0);
    }

    private Pair<Team, List<TeamGenerationData>> getRandomTeam(Team teamToMatchAgainst, int teamToMatchAgainstIndex, List<Pair<Team, List<TeamGenerationData>>> teams, Game game, List<Team> alreadygeneratedTeams) {
        List<Integer> alreadyTriedIndices = new ArrayList<>();
        int teamIndex = this.getTeamIndex(teams.size(), alreadyTriedIndices);
        alreadyTriedIndices.add(teamIndex);
        int securityIndex = 0;
        while (!this.isTeamIndexValid(teamToMatchAgainst, teamToMatchAgainstIndex, teams, teamIndex, game, alreadygeneratedTeams)) {
            teamIndex = this.getTeamIndex(teams.size(), alreadyTriedIndices);
            alreadyTriedIndices.add(teamIndex);
            if (securityIndex > 10) {
                LOGGER.error("cant match team");
                // throw this exception because this is a possible endless loop if you are unlucky
                throw new GenerationException(null, "Please try again, maybe you get more luck,");
            }
            securityIndex++;
        }
        return teams.get(teamIndex);
    }

    private int getTeamIndex(int bound, List<Integer> alreadyTriedIndices) {
        Random random = new Random();
        int randomInt = random.nextInt(bound);
        int securityIndex = 0;
        while (alreadyTriedIndices.contains(randomInt)) {
            randomInt = random.nextInt(bound);
            securityIndex++;
            if (securityIndex > 1000) {
                return 5;
            }
        }
        return randomInt;
    }

    private boolean isTeamIndexValid(Team teamToMatchAgainst, int teamToMatchAgainstIndex, List<Pair<Team, List<TeamGenerationData>>> teams, int teamIndex, Game game, List<Team> alreadyGeneratedTeams) {
        List<TeamGenerationData> data = teams.get(teamIndex).getSecond();
        return teams.get(teamIndex).getFirst() != teamToMatchAgainst
                && Math.abs(teamToMatchAgainstIndex - teamIndex) > 1
                && data.stream().noneMatch(x -> x.getPlayedAgainst().contains(teamToMatchAgainst))
                && !alreadyGeneratedTeams.contains(teams.get(teamIndex).getFirst());
    }

    private void updateTeam(Category category, Team team) {
        team.setCategory(category);
        this.teamService.save(team);
    }
}
