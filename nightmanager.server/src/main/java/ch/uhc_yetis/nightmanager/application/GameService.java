package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.GameState;
import ch.uhc_yetis.nightmanager.domain.model.GameType;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import ch.uhc_yetis.nightmanager.domain.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final HallService hallService;
    private final CategoryService categoryService;
    private final GameSpecifications gameSpecifications;

    public GameService(GameRepository gameRepository, HallService hallService, CategoryService categoryService, GameSpecifications gameSpecifications) {
        this.gameRepository = gameRepository;
        this.hallService = hallService;
        this.categoryService = categoryService;
        this.gameSpecifications = gameSpecifications;
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
        List<Game> games = this.gameRepository.findAll(this.gameSpecifications.withStateCategoryAndHall(requestParams.getState(), requestParams.getCategoryId(), requestParams.getHallId()));
        List<Game> gamesSorted = games.stream().sorted(Comparator.comparing(Game::getStartDate)).collect(Collectors.toList());
        if (requestParams.getAfterNow() != null && requestParams.getBeforeNow() != null && games.size() >= requestParams.getAfterNow() + requestParams.getBeforeNow()) {
            LocalDateTime now = LocalDateTime.now();
            Game closestToNow = this.getDateClosestToDate(gamesSorted, now);
            int index = IntStream.range(0, gamesSorted.size()).filter(x -> gamesSorted.get(x).getId() == closestToNow.getId()).findFirst().getAsInt();
            int fromIndex = index - requestParams.getBeforeNow() > 0 ? index - requestParams.getBeforeNow() : 0;
            int toIndex = index - requestParams.getAfterNow() > gamesSorted.size() - 1 ? index - requestParams.getAfterNow() : gamesSorted.size();
            return gamesSorted.subList(fromIndex, toIndex);
        }
        return gamesSorted;
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
        return this.gameRepository.findAllByTeamGuestOrTeamHomeAndType(team, team, GameType.GROUP_STAGE);
    }
}
