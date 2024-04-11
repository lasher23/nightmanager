package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @ManyToOne
    @JoinColumn(name = "fk_team_home")
    private Team teamHome;
    @ManyToOne
    @JoinColumn(name = "fk_team_guest")
    private Team teamGuest;
    @Column(name = "goals_team_guest")
    private int goalsTeamGuest;
    @Column(name = "goals_team_home")
    private int goalsTeamHome;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private GameState state;
    @Column(name = "flagPlaceholder")
    private boolean placeholder;

    @Column(name = "fk_hall", insertable = false, updatable = false)
    private Long hallId;
    @ManyToOne
    @JoinColumn(name = "fk_hall")
    private Hall hall;
    @Column(name = "fk_category", insertable = false, updatable = false)
    private Long categoryId;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;
    @Column
    private LocalDateTime startDate;
    @Column(nullable = false)
    private long duration;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private GameType type;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean live;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "game_notification_log_assignment",
            joinColumns = @JoinColumn(name = "fk_game"), inverseJoinColumns = @JoinColumn(name = "fk_notification"))
    private List<NotificationLog> notifications;

    @Column(columnDefinition = "boolean default false")
    private boolean swappedLiveDisplay = false;
    @Column(columnDefinition = "boolean default false")
    private boolean swappedReferee = false;

    public boolean isSwappedLiveDisplay() {
        return swappedLiveDisplay;
    }

    public void setSwappedLiveDisplay(boolean swappedLiveDisplay) {
        this.swappedLiveDisplay = swappedLiveDisplay;
    }

    public boolean isSwappedReferee() {
        return swappedReferee;
    }

    public void setSwappedReferee(boolean swappedReferee) {
        this.swappedReferee = swappedReferee;
    }

    public GameType getType() {
        return this.type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Hall getHall() {
        return this.hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPlaceholder() {
        return this.placeholder;
    }

    public void setPlaceholder(boolean placeholder) {
        this.placeholder = placeholder;
    }

    public long getId() {
        return this.id;
    }

    public Team getTeamHome() {
        return this.teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamGuest() {
        return this.teamGuest;
    }

    public void setTeamGuest(Team teamGuest) {
        this.teamGuest = teamGuest;
    }

    public int getGoalsTeamGuest() {
        return this.goalsTeamGuest;
    }

    public void setGoalsTeamGuest(int goalsTeamGuest) {
        this.goalsTeamGuest = goalsTeamGuest;
    }

    public int getGoalsTeamHome() {
        return this.goalsTeamHome;
    }

    public void setGoalsTeamHome(int goalsTeamHome) {
        this.goalsTeamHome = goalsTeamHome;
    }

    public GameState getState() {
        return this.state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public List<NotificationLog> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationLog> notifications) {
        this.notifications = notifications;
    }
}
