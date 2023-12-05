package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.*;
import ch.uhc_yetis.nightmanager.domain.repository.TeamRepository;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {
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
        List<Team> persistedTeam = this.teamRepository.findByCategory(category);
        persistedTeam.stream().filter(team -> team.getNotifications().isEmpty())
                .filter(team -> team.getPhoneNumber() != null)
                .forEach(team -> {
                    String gamesMessage = this.gameService.findGamesOfTeam(team).stream().map(game -> {
                        Team enemy = team.getId() == game.getTeamHome().getId() ? game.getTeamGuest() : game.getTeamHome();
                        return game.getStartDate().format(DateTimeFormatter.ofPattern("HH:mm")) +
                                " gegen " + enemy.getName() + ", in der Halle " + game.getHall().getName();
                    }).collect(Collectors.joining("\n"));
                    String message = "Dein Team hat folgende Spiele Heute:\n" + gamesMessage + "\nVerfolge deine Spiele unter: https://night.nicischmid.ch/display";
                    if (StringUtils.hasText(category.getAdditionalSmsText())) {
                        message += "\n" + category.getAdditionalSmsText();
                    }
                    NotificationLog notification = new NotificationLog();
                    notification.setReference("team-start-message-" + team.getId());
                    notification.setSentTime(OffsetDateTime.now());
                    notification.setSuccess(true);
                    notification.setToNumber(team.getPhoneNumber());
                    notification.setText(message);
                    notificationService.sendNotification(notification.getText(), notification.getToNumber());
                    team.setNotifications(List.of(notification));
                    this.save(team);
                });
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
}
