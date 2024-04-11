package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.*;
import ch.uhc_yetis.nightmanager.domain.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);
    private final GameRepository gameRepository;
    private final HallService hallService;
    private final CategoryService categoryService;
    private final SimpMessagingTemplate template;
    private final NotificationService notificationService;

    public GameService(GameRepository gameRepository, HallService hallService, CategoryService categoryService,
                       SimpMessagingTemplate template, NotificationService notificationService) {
        this.gameRepository = gameRepository;
        this.hallService = hallService;
        this.categoryService = categoryService;
        this.template = template;
        this.notificationService = notificationService;
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

    public Optional<Game> reset(Game game) {
        return this.gameRepository.findById(game.getId())
                .map(foundGame -> {
                    foundGame.setGoalsTeamGuest(0);
                    foundGame.setGoalsTeamHome(0);
                    foundGame.setState(GameState.OPEN);
                    return foundGame;
                }).map(this::save);
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

    @Scheduled(fixedRate = 20000)
    public void notifyUpcomingGames() {
        LOGGER.info("notifying games");
        this.gameRepository.findAllByStartDateBetween(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15))
                .stream()
                .filter(game -> game.getCategory() != null)
                .filter(game -> game.getCategory().getState() == CategoryState.GROUP_PHASE || game.getCategory().getState() == CategoryState.CROKI_FIRST)
                .forEach(this::notify);
    }

    public Optional<Game> notify(Game game) {
        LOGGER.info("Nofifying Game '{}'", game.getId());
        return getById(game.getId())
                .map(persitedGame -> {
                    List<NotificationLog> newNotifications = new ArrayList<>();
                    if (StringUtils.hasText(persitedGame.getTeamGuest().getPhoneNumber())
                            && notificationService.getAllNotifications().stream().noneMatch(existingNotification -> existingNotification.getReference().equals(createNotificationReference(game, game.getTeamGuest())))) {
                        newNotifications.add(createNotification(persitedGame, persitedGame.getTeamGuest()));
                    }
                    if (StringUtils.hasText(persitedGame.getTeamHome().getPhoneNumber())
                            && notificationService.getAllNotifications().stream().noneMatch(existingNotification -> existingNotification.getReference().equals(createNotificationReference(game, game.getTeamHome())))) {
                        newNotifications.add(createNotification(persitedGame, persitedGame.getTeamHome()));
                    }
                    persitedGame.getNotifications().addAll(newNotifications);
                    newNotifications.forEach(notification -> {
                        try {
                            notificationService.sendNotification(notification.getText(), notification.getToNumber());
                        } catch (Exception e) {
                            LOGGER.warn("Error sending the notification: " + notification);
                            notification.setSuccess(false);
                        }
                    });
                    return gameRepository.save(persitedGame);
                });
    }

    private static NotificationLog createNotification(Game game, Team team) {
        NotificationLog notification = new NotificationLog();
        String time = game.getStartDate().format(DateTimeFormatter.ofPattern("HH:mm"));
        String hall = game.getHall().getName();
        String home = game.getTeamHome().getName();
        String guest = game.getTeamGuest().getName();
        String text = "Hey Team " + team.getName() + ", euer nächstes Spiel steht an:\n" +
                " - " + time + " Uhr, " + home + " vs. " + guest + " (Halle " + hall + ")\n" +
                "Macht euch bereit und viel Spass\n" +
                "Eure Yetis";
        notification.setText(text);
        notification.setToNumber(team.getPhoneNumber());
        notification.setSentTime(OffsetDateTime.now());
        notification.setReference(createNotificationReference(game, team));
        notification.setSuccess(true);
        return notification;
    }

    private static String createNotificationReference(Game game, Team team) {
        return "Game-" + game.getId() + "-Team-" + team.getId();
    }

    private static List<NotificationLog> addToList(List<NotificationLog> list, NotificationLog notificationLog) {
        if (list == null) {
            return List.of(notificationLog);
        }
        List<NotificationLog> newList = new ArrayList<>(list);
        newList.add(notificationLog);
        return newList;
    }

    public List<Game> findGamesOfTeam(Team team) {
        return this.gameRepository.findAllByTeamHomeOrTeamGuestOrderByStartDate(team, team);
    }

    public List<Game> findGamesOfTeamAndType(Team team, GameType gameType) {
        return this.gameRepository.findAllByTeamAndType(team, gameType);
    }

    public List<Game> findGamesOfTeamAndCategory(Team team, Category category) {
        return this.gameRepository.findAllByTeamAndCategoryOrderByStartDate(team, category);
    }

    public Optional<Game> swapTeams(Game game) {
        return this.gameRepository.findById(game.getId()).map(persistedGame -> {
            persistedGame.setSwappedReferee(game.isSwappedReferee());
            persistedGame.setSwappedLiveDisplay(game.isSwappedLiveDisplay());
            return this.save(persistedGame);
        });
    }
}
