package ch.uhc_yetis.nightmanager.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import ch.uhc_yetis.nightmanager.domain.enumeration.GameState;
import ch.uhc_yetis.nightmanager.domain.enumeration.GameType;

/**
 * A DTO for the {@link ch.uhc_yetis.nightmanager.domain.Game} entity.
 */
public class GameDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer goalsHome;

    @NotNull
    private Integer goalsGuest;

    @NotNull
    private GameState state;

    @NotNull
    private Boolean placeholder;

    @NotNull
    private ZonedDateTime start;

    private Long duration;

    @NotNull
    private GameType type;


    private Long homeId;

    private Long guestId;

    private Long categoryId;

    private Long hallId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(Integer goalsHome) {
        this.goalsHome = goalsHome;
    }

    public Integer getGoalsGuest() {
        return goalsGuest;
    }

    public void setGoalsGuest(Integer goalsGuest) {
        this.goalsGuest = goalsGuest;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Boolean isPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Boolean placeholder) {
        this.placeholder = placeholder;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public Long getHomeId() {
        return homeId;
    }

    public void setHomeId(Long teamId) {
        this.homeId = teamId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long teamId) {
        this.guestId = teamId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GameDTO gameDTO = (GameDTO) o;
        if (gameDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gameDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GameDTO{" +
            "id=" + getId() +
            ", goalsHome=" + getGoalsHome() +
            ", goalsGuest=" + getGoalsGuest() +
            ", state='" + getState() + "'" +
            ", placeholder='" + isPlaceholder() + "'" +
            ", start='" + getStart() + "'" +
            ", duration=" + getDuration() +
            ", type='" + getType() + "'" +
            ", home=" + getHomeId() +
            ", guest=" + getGuestId() +
            ", category=" + getCategoryId() +
            ", hall=" + getHallId() +
            "}";
    }
}
