package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.*;
import ch.uhc_yetis.nightmanager.domain.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final SimpMessagingTemplate template;

    public GameService(GameRepository gameRepository, HallService hallService, CategoryService categoryService,
                       SimpMessagingTemplate template) {
        this.gameRepository = gameRepository;
        this.hallService = hallService;
        this.categoryService = categoryService;
        this.template = template;
    }

    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    public Game createNewGame(Game game) {
        game.setId(0);
        return this.save(game);
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
        return this.gameRepository
                .findByHallAndCategory(this.hallService.getById(hallId), this.categoryService.findById(categoryId));
    }

    public Game complete(Game game) {
        if (this.gameRepository.existsById(game.getId())) {
            game.setState(GameState.DONE);
            return this.save(game);
        }
        throw new CustomException("Spiel " + game.getId() + " exisiert nicht", Status.NOT_FOUND);
    }

    public List<Game> getAll(GameRequestParams requestParams) {
        Specification<Game> gameSpecs = this.getGameSpecs(requestParams);
        List<Game> games = this.gameRepository.findAll(gameSpecs);
        List<Game> gamesSorted = games.stream().sorted(Comparator.comparing(Game::getStartDate))
                .collect(Collectors.toList());
        return gamesSorted;
    }

    private Specification<Game> getGameSpecs(GameRequestParams requestParams) {
        return new GameSpecificationsBuilder()
                .withCategory(requestParams.getCategoryId())
                .withHall(requestParams.getHallId())
                .withState(requestParams.getState())
                .withLive(requestParams.getLive())
                .build();
    }

    public List<Game> getAllFromTeam(Team team) {
        return this.gameRepository.findAllByTeamGuestOrTeamHome(team, team);
    }

    public Game reset(Game game) {
        game.setGoalsTeamGuest(0);
        game.setGoalsTeamHome(0);
        game.setState(GameState.OPEN);
        return this.save(game);
    }

    public List<Game> getAllGamesByTypeAndTeamAndState(Team team, GameType type, GameState state) {
        return this.gameRepository.findAllByTeamGuestOrTeamHomeAndTypeAndState(team, team, type, state);
    }

    public List<Game> getAllGamesByCategoryAndType(Category category, GameType gameType) {
        return this.gameRepository.findAllByCategoryAndType(category, gameType);
    }

    public Optional<Game> findGameByTwoTeamsAndType(Team team1, Team team2, GameType gameType) {
        return this.gameRepository.findByTwoTeamsAndType(team1, team2, gameType);
    }

    public List<Game> getGamesByGameTypeAndCategory(GameType gameType, Category category) {
        return this.gameRepository.findAllByCategoryAndType(category, gameType);
    }

    public Game save(Game firstSemi) {
        Game newGame = this.gameRepository.save(firstSemi);
        this.template.convertAndSend("/topic/nightmanager-game-change", "{\"status\":\"GameChanged\"}");
        return newGame;
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
        Optional<Team> winner = getWinner(game);
        if (winner.isPresent() && game.getTeamHome() == winner.get()) {
            return Optional.of(game.getTeamGuest());
        } else if (winner.isPresent() && game.getTeamGuest() == winner.get()) {
            return Optional.of(game.getTeamHome());
        }
        return Optional.empty(); // TIED GAME
    }

    public Game updateLive(Game game, boolean live) {
        if (live) {
            this.gameRepository.findByHallAndLive(game.getHall(), true)
                    .stream()
                    .peek(liveGame -> liveGame.setLive(false))
                    .forEach(this.gameRepository::save);
        }
        game.setLive(live);
        var savedGame = save(game);
        this.template.convertAndSend("/topic/nightmanager-game-change", "{\"status\":\"GameChanged\"}");
        return savedGame;
    }

    public Game getNextGame(Game game) {
        return this.gameRepository.findFirstByStartDateGreaterThanAndHallOrderByStartDate(game.getStartDate(), game.getHall());
    }
}
