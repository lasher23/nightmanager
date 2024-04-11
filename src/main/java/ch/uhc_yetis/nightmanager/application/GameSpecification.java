package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Game;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class GameSpecification implements Specification<Game> {
    private SearchCriteria criteria;

    public GameSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Game> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (this.criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(this.criteria.getKey()), this.criteria.getValue().toString());
        } else if (this.criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(this.criteria.getKey()), this.criteria.getValue().toString());
        } else if (this.criteria.getOperation().equalsIgnoreCase("=")) {
            return criteriaBuilder.equal(root.get(this.criteria.getKey()), this.criteria.getValue());
        }
        return null;
    }
}
