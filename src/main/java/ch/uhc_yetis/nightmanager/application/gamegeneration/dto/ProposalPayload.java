package ch.uhc_yetis.nightmanager.application.gamegeneration.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The editable content of a proposal (everything that is persisted as JSON
 * in {@link ch.uhc_yetis.nightmanager.domain.model.GameGenerationProposal#getPayload()}).
 */
public class ProposalPayload {
    private List<ProposedCategoryDto> categories = new ArrayList<>();
    private List<ProposedGameDto> games = new ArrayList<>();

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
