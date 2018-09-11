package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.GameState;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;

@Component
public final class GameSpecifications {

    private CategoryService categoryService;
    private HallService hallService;

    public GameSpecifications(CategoryService categoryService, HallService hallService) {
        this.categoryService = categoryService;
        this.hallService = hallService;
    }

    public Specification<Game> withCategory(long categoryId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), this.categoryService.findById(categoryId));
    }

    public Specification<Game> withHall(long hallId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hall"), this.hallService.getById(hallId)));
    }

    public Specification<Game> withStateCategoryAndHall(GameState state, Long categoryId, Long hallId) {
        return (root, query, criteriaBuilder) -> {
            Predicate disjunction = criteriaBuilder.disjunction();
            if (state != null) {
                disjunction.getExpressions().add(criteriaBuilder.equal(root.get("state"), state));
            }
            if (categoryId != null) {
                disjunction.getExpressions().add(criteriaBuilder.equal(root.get("category"), this.categoryService.findById(categoryId)));
            }
            if (hallId != null) {
                disjunction.getExpressions().add(criteriaBuilder.equal(root.get("hall"), this.hallService.getById(hallId)));
            }
            return disjunction;
        };
    }
}