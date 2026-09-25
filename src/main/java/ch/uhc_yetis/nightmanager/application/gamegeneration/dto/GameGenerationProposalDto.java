package ch.uhc_yetis.nightmanager.application.gamegeneration.dto;

import ch.uhc_yetis.nightmanager.domain.model.ProposalStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class GameGenerationProposalDto {
    private Long id;
    private Long tournamentId;
    private String name;
    private LocalDateTime startTime;
    private ProposalStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private List<ProposedCategoryDto> categories;
    private List<ProposedGameDto> games;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ProposedCategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<ProposedCategoryDto> categories) {
        this.categories = categories;
    }

    public List<ProposedGameDto> getGames() {
        return games;
    }

    public void setGames(List<ProposedGameDto> games) {
        this.games = games;
    }
}
