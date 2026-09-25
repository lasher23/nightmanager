package ch.uhc_yetis.nightmanager.application.gamegeneration.dto;

import ch.uhc_yetis.nightmanager.domain.model.GameType;

import java.time.LocalDateTime;

public class ProposedGameDto {
    private String tempId;
    private String categoryRef;
    private Long hallId;
    private LocalDateTime startDate;
    private GameType type;
    private TeamRefDto teamHome;
    private TeamRefDto teamGuest;
    private boolean placeholder;

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(String categoryRef) {
        this.categoryRef = categoryRef;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public TeamRefDto getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(TeamRefDto teamHome) {
        this.teamHome = teamHome;
    }

    public TeamRefDto getTeamGuest() {
        return teamGuest;
    }

    public void setTeamGuest(TeamRefDto teamGuest) {
        this.teamGuest = teamGuest;
    }

    public boolean isPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(boolean placeholder) {
        this.placeholder = placeholder;
    }
}
