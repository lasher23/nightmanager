package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.GameState;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class GameSpecificationsBuilder {
    public static final String EQUALS = "=";
    private final List<SearchCriteria> params = new ArrayList<>();

    public GameSpecificationsBuilder withCategory(Long categoryId) {
        if (Objects.nonNull(categoryId) && categoryId > 0) {
            this.params.add(new SearchCriteria("categoryId", EQUALS, categoryId));
        }
        return this;
    }

    public GameSpecificationsBuilder withLive(Boolean live) {
        if (Objects.nonNull(live)) {
            this.params.add(new SearchCriteria("live", EQUALS, live));
        }
        return this;
    }

    public GameSpecificationsBuilder withState(GameState gameState) {
        if (Objects.nonNull(gameState)) {
            this.params.add(new SearchCriteria("state", EQUALS, gameState));
        }
        return this;
    }

    public GameSpecificationsBuilder withHall(Long hallId) {
        if (Objects.nonNull(hallId) && hallId > 0) {
            this.params.add(new SearchCriteria("hallId", EQUALS, hallId));
        }
        return this;
    }

    public Specification<Game> build() {
        if (this.params.size() == 0) {
            return null;
        }

        List<Specification> specs = this.params.stream().map(GameSpecification::new).collect(Collectors.toList());
        Specification result = specs.get(0);

        for (int i = 1; i < this.params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
