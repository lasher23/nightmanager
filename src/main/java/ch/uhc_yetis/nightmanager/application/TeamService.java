package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.*;
import ch.uhc_yetis.nightmanager.domain.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);

    private TeamRepository teamRepository;
    private CategoryService categoryService;
    private GameService gameService;
    private final NotificationService notificationService;

    public TeamService(TeamRepository teamRepository, CategoryService categoryService, GameService gameService, NotificationService notificationService) {
        this.teamRepository = teamRepository;
        this.categoryService = categoryService;
        this.gameService = gameService;
        this.notificationService = notificationService;
    }

    public Team createNewTeam(Team team) {
        team.setId(0);
        return this.teamRepository.save(team);
    }

    public List<TeamDto> findAll() {
        return this.teamRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public Optional<Team> findById(long id) {
        return this.teamRepository.findById(id);
    }

    public Team save(Team team) {
        return this.teamRepository.save(team);
    }

    public List<TeamDto> findByCategory(Long categoryId) {
        return this.teamRepository.findByCategory(this.categoryService.findById(categoryId)).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void notifyCategory(Category category) {
        Boolean yetisCupChild = Optional.ofNullable(category.getParentCategory()).map(Category::getType).map(type -> type.equals(CategoryType.YETIS_CUP)).orElse(false);
        LOGGER.info("Sending notification for category '{}', yetisCupChild '{}', state '{}'", category.getName(), yetisCupChild, category.getState());
        if ((category.getState() == CategoryState.GROUP_PHASE && !yetisCupChild) || category.getState() == CategoryState.CROKI_FIRST) {
            Function<Team, String> notificationProvider = (team) -> {
                List<Game> gamesOfTeam = this.gameService.findGamesOfTeam(team);
                String gamesMessage = createGamesMessage(gamesOfTeam);
                return "Liebes Team " + team.getName() + "\n" +
                        "Herzlich Willkommen am Yetis Turnier 2023. " +
                        "Ihr spielt heute in der Kategorie " + team.getCategory().getName().replace("Kategorie", "") + " und habt folgende Gruppenspiele vor euch:\n" +
                        gamesMessage + "\n" +
                        "Ihr werdet jeweils auf diesem Kanal 15min vor Spielbeginn über euer nächstes Spiel informiert. " +
                        "In der Zwischenzeit findet ihr alle Spiele und Resultate unter https://night.nicischmid.ch/display \n" +
                        "Cool seid ihr heute mit dabei. Auf ein unvergesslich, faires Turnier.\n" +
                        "Eure Yetis Hildisrieden";
            };
            notifyCategory(category, "team-start-message", notificationProvider);
        } else if (category.getState() == CategoryState.GROUP_PHASE && yetisCupChild) {
            Function<Team, String> notificationProvider = (team) -> {
                List<Game> gamesOfTeam = this.gameService.findGamesOfTeamAndCategory(team, category);
                String gamesMessage = createGamesMessage(gamesOfTeam);
                return "Team " + team.getName() + "\n" +
                        "Die neuen Partien für den Yetis Cup in der Kategorie Plausch sind ausgelost. Euer Team wurde in die Kategorie " + category.getName() + " eingeteilt.\n" +
                        "Eure nächsten Spiele sind:\n" +
                        gamesMessage + "\n" +
                        "Auf weitere spannende Spiele...\n" +
                        "Eure Yetis";
            };
            notifyCategory(category, "team-yetis-message", notificationProvider);
        } else if (category.getState() == CategoryState.SEMI_FINAL) {
            LOGGER.info("Sending notification for category state semi final");
            Function<Team, String> notificationProvider = (team) -> {
                List<Game> gamesOfTeam = this.gameService.findGamesOfTeamAndType(team, GameType.SEMI_FINAL);
                if (gamesOfTeam.isEmpty()) {
                    LOGGER.info("Not sending semi final notification for team " + team.getName() + " because no games were found");
                    return null;
                }
                String gamesMessage = createGamesMessage(gamesOfTeam);
                return "Team " + team.getName() + "\n" +
                        "Gratulation! Ihr habt euch für das Halbfinale qualifiziert. Euer nächstes Spiel ist:\n" +
                        gamesMessage + "\n" +
                        "Viel Erfolg\n" +
                        "Eure Yetis";
            };
            notifyCategory(category, "team-semi-final-message", notificationProvider);

        } else if (category.getState() == CategoryState.FINAL) {
            Function<Team, String> notificationProvider = (team) -> {
                List<Game> gamesOfTeam = this.gameService.findGamesOfTeamAndType(team, GameType.FINAL);
                if (gamesOfTeam.isEmpty()) {
                    return null;
                }
                String gamesMessage = createGamesMessage(gamesOfTeam);
                boolean isFinal = gamesOfTeam.get(0).getHall().getId() == 1;
                String text = isFinal ? "Gratulation! Ihr habt euch für das Final qualifiziert." : "Ihr seit im kleinen Final.";
                return "Team " + team.getName() + "\n" +
                        text + " Euer nächstes Spiel ist:\n" +
                        gamesMessage + "\n" +
                        "Viel Erfolg\n" +
                        "Eure Yetis";
            };
            notifyCategory(category, "team-final-message", notificationProvider);
        } else if (category.getState() == CategoryState.FINISHED) {
            Function<Team, String> notificationProvider = (team) -> {
                String ranking = findByCategoryExcludingPlaceholders(category).stream().sorted(Comparator.comparing(TeamDto::getRank)).map(t -> t.getRank() + ". " + t.getName()).collect(Collectors.joining("\n"));
                return "Liebes Team " + team.getName() + "\n" +
                        "Ein grossartiges Turnier ist nun zu Ende. Hier ist die Endrangliste deiner Kategorie.\n" +
                        ranking + "\n" +
                        "Herzlichen Dank für eure Teilnahme und bis zum nächsten Jahr am Yetis Turnier 14.12.2024.\n" +
                        "Eure Yetis Hildisrieden";
            };
            notifyCategory(category, "team-finish-message", notificationProvider);
        }
    }

    private void notifyCategory(Category category, String notificationType, Function<Team, String> notificationProvider) {
        LOGGER.info("Start notification '{}' for category '{}'", notificationType, category.getName());
        List<Team> persistedTeam = this.teamRepository.findByCategory(category);
        LOGGER.info("Following Teams: " + persistedTeam.toString());
        persistedTeam
                .forEach(team -> {
                    String message = notificationProvider.apply(team);
                    if (message == null) {
                        LOGGER.info("Not sending message because its null");
                        return;
                    }
                    NotificationLog notification = new NotificationLog();
                    notification.setReference(getNotReference(notificationType, team));
                    notification.setSentTime(OffsetDateTime.now());
                    notification.setSuccess(true);
                    notification.setTagId("team-" + team.getId());
                    notification.setText(message);
                    notification.setUrl("/v2/public/games?teamId=" + team.getId());
                    try {
                        notificationService.sendNotification(notification.getText(), notification.getTagId(), notification.getUrl());
                    } catch (Exception e) {
                        LOGGER.warn("Received error setting notification success to false", e);
                        notification.setSuccess(false);
                    }
                    List<NotificationLog> list = new ArrayList<>();
                    if (team.getNotifications() != null) {
                        list.addAll(team.getNotifications());
                    }
                    list.add(notification);
                    team.setNotifications(list);
                    this.save(team);
                });
    }

    private static String createGamesMessage(List<Game> gamesOfTeam) {
        return gamesOfTeam.stream().map(game ->
        {
            String time = game.getStartDate().format(DateTimeFormatter.ofPattern("HH:mm"));
            String home = game.getTeamHome().getName();
            String guest = game.getTeamGuest().getName();
            String hall = game.getHall().getName();
            return " - " + time + " Uhr, " + home + " vs. " + guest + " (Halle " + hall + ")";
        }).collect(Collectors.joining("\n"));
    }

    private static String getNotReference(String prefix, Team team) {
        return prefix + "-" + team.getId();
    }

    private TeamDto mapToDto(Team team) {
        return TeamDto//
                .withId(team.getId())
                .withName(team.getName())
                .withCategory(team.getCategory())
                .withPlaceholder(team.isPlaceholder())
                .withGoalsShot(this.getGoalsShot(team))
                .withGoalsGotten(this.getGoalsGotten(team))
                .withPoints(this.getPoints(team))
                .withRank(team.getRank())
                .withPaid(team.isPaid())
                .build();
    }

    private int getPoints(Team team) {
        return this.gameService.getAllGamesByTypeAndTeamAndState(team, GameType.GROUP_STAGE, GameState.DONE).stream()
                .map(game -> this.getPointsForTeam(team, game))
                .mapToInt(value -> value).sum();
    }

    private int getPointsForTeam(Team team, Game game) {
        if (game.getGoalsTeamGuest() > game.getGoalsTeamHome()) {
            if (game.getTeamGuest().getId() == team.getId()) {
                return 3;
            } else {
                return 0;
            }
        } else if (game.getGoalsTeamGuest() < game.getGoalsTeamHome()) {
            if (game.getTeamHome().getId() == team.getId()) {
                return 3;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    private int getGoalsGotten(Team team) {
        List<Game> allGamesFromTeam = this.gameService.getAllFromTeam(team);
        return allGamesFromTeam.stream().map(x -> {
            if (x.getTeamHome().getId() == team.getId()) {
                return x.getGoalsTeamGuest();
            } else if (x.getTeamGuest().getId() == team.getId()) {
                return x.getGoalsTeamHome();
            }
            return 0;
        }).mapToInt(Integer::intValue).sum();
    }

    private int getGoalsShot(Team team) {
        List<Game> allGamesFromTeam = this.gameService.getAllFromTeam(team);
        return allGamesFromTeam.stream().map(x -> {
            if (x.getTeamHome().getId() == team.getId()) {
                return x.getGoalsTeamHome();
            } else if (x.getTeamGuest().getId() == team.getId()) {
                return x.getGoalsTeamGuest();
            }
            return 0;
        }).mapToInt(Integer::intValue).sum();
    }

    public List<TeamDto> findByCategory(Category category) {
        return this.teamRepository.findByCategory(category).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<TeamDto> findByCategoryExcludingPlaceholders(Category category) {
        return this.teamRepository.findByCategoryAndPlaceholderIsFalse(category).stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
