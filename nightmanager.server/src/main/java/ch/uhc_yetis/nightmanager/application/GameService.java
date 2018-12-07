package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.*;
import ch.uhc_yetis.nightmanager.domain.repository.GameRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final HallService hallService;
    private final CategoryService categoryService;
    private TeamService teamService;

    public GameService(GameRepository gameRepository, HallService hallService, CategoryService categoryService, TeamService teamService) {
        this.gameRepository = gameRepository;
        this.hallService = hallService;
        this.categoryService = categoryService;
        this.teamService = teamService;
    }

    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    public Game createNewGame(Game game) {
        game.setId(0);
        return this.gameRepository.save(game);
    }


    public Optional<Game> getById(long id) {
        return this.gameRepository.findById(id);
    }

    public List<Game> getAllByHall(long hallId) {
        return this.gameRepository.findByHall(this.hallService.getById(hallId));
    }

    public List<Game> getAllByCategory(long categoryId) {
        return this.gameRepository.findByCategory(this.categoryService.findById(categoryId));
    }

    public List<Game> getAllByHallAndCategory(long hallId, long categoryId) {
        return this.gameRepository.findByHallAndCategory(this.hallService.getById(hallId), this.categoryService.findById(categoryId));
    }

    public Game complete(Game game) {
        if (this.gameRepository.existsById(game.getId())) {
            game.setState(GameState.DONE);
            return this.gameRepository.save(game);
        }
        throw new CustomException("Spiel " + game.getId() + " exisiert nicht", Status.NOT_FOUND);
    }

    public List<Game> getAll(GameRequestParams requestParams) {
        Specification<Game> gameSpecs = this.getGameSpecs(requestParams);
        List<Game> games = this.gameRepository.findAll(gameSpecs);
        List<Game> gamesSorted = games.stream().sorted(Comparator.comparing(Game::getStartDate)).collect(Collectors.toList());
        return gamesSorted;
    }

    private Specification<Game> getGameSpecs(GameRequestParams requestParams) {
        return new GameSpecificationsBuilder()
                .withCategory(requestParams.getCategoryId())
                .withHall(requestParams.getHallId())
                .withState(requestParams.getState())
                .build();
    }

    private Game getDateClosestToDate(List<Game> games, LocalDateTime now) {
        Game game = Collections.min(games, (d1, d2) -> {
            long diff1 = Duration.between(d1.getStartDate(), now).getSeconds();
            long diff2 = Duration.between(d2.getStartDate(), now).getSeconds();
            return Long.compare(diff1, diff2);
        });
        return game;
    }

    public List<Game> getAllFromTeam(Team team) {
        return this.gameRepository.findAllByTeamGuestOrTeamHome(team, team);
    }

    public Game reset(Game game) {
        game.setGoalsTeamGuest(0);
        game.setGoalsTeamHome(0);
        game.setState(GameState.OPEN);
        return this.gameRepository.save(game);
    }

    public List<Game> getAllGroupstageGamesFromTeam(Team team) {
        return this.gameRepository.findAllByTeamGuestOrTeamHomeAndTypeAndState(team, team, GameType.GROUP_STAGE, GameState.DONE);
    }

    public List<Game> getAllGamesByCategoryAndType(Category category, GameType gameType) {
        return this.gameRepository.findAllByCategoryAndType(category, gameType);
    }

    public List<Game> getAllByCategory(Category category) {
        return this.gameRepository.findByCategory(category);
    }

    public Optional<Game> findGameByTwoTeamsAndType(Team team1, Team team2, GameType gameType) {
        return this.gameRepository.findByTwoTeamsAndType(team1, team2, gameType);
    }

    public List<Game> getGamesByGameTypeAndCategory(GameType gameType, Category category) {
        return this.gameRepository.findAllByCategoryAndType(category, gameType);
    }

    public Game save(Game firstSemi) {
        return this.gameRepository.save(firstSemi);
    }

    public Optional<Team> getWinner(Game game) {
        if (game.getGoalsTeamHome() > game.getGoalsTeamGuest()) {
            return Optional.of(game.getTeamHome());
        } else if (game.getGoalsTeamHome() < game.getGoalsTeamGuest()) {
            return Optional.of(game.getTeamGuest());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Team> getLooser(Game game) {
        if (game.getGoalsTeamHome() < game.getGoalsTeamGuest()) {
            return Optional.of(game.getTeamHome());
        } else if (game.getGoalsTeamHome() > game.getGoalsTeamGuest()) {
            return Optional.of(game.getTeamGuest());
        } else {
            return Optional.empty();
        }
    }

    public CompareResultModel compare(CompareModel compareModel) {
        Optional<Game> game = this.findGameByTwoTeamsAndType(compareModel.getTeam1(), compareModel.getTeam2(), GameType.GROUP_STAGE);
        if (game.isPresent() && game.get().getTeamHome().getId() == compareModel.getTeam1().getId()) {
            return new CompareResultModel(Integer.compare(game.get().getGoalsTeamHome(), game.get().getGoalsTeamGuest()));
        } else if (game.isPresent() && game.get().getTeamGuest().getId() == compareModel.getTeam1().getId()) {
            return new CompareResultModel(Integer.compare(game.get().getGoalsTeamGuest(), game.get().getGoalsTeamHome()));
        } else {
            return  new CompareResultModel(0);
        }
    }
}
