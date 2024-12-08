package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.application.GameRequestParams;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private GameRepository gameRepository;

    public List<Game> findGames(GameRequestParams criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> query = cb.createQuery(Game.class);
        Root<Game> game = query.from(Game.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getCategoryId() != null) {
            predicates.add(cb.equal(game.get("category").get("id"), criteria.getCategoryId()));
        }

        if (criteria.getLive() != null) {
            predicates.add(cb.equal(game.get("live"), criteria.getLive()));
        }

        if (criteria.getState() != null) {
            predicates.add(cb.equal(game.get("state"), criteria.getState()));
        }

        if (criteria.getHallId() != null) {
            predicates.add(cb.equal(game.get("hall").get("id"), criteria.getHallId()));
        }

        if (criteria.getTeamId() != null) {
            Predicate homeTeamPredicate = cb.equal(game.get("teamHome").get("id"), criteria.getTeamId());
            Predicate guestTeamPredicate = cb.equal(game.get("teamGuest").get("id"), criteria.getTeamId());
            predicates.add(cb.or(homeTeamPredicate, guestTeamPredicate));
        }

        if (predicates.isEmpty()) {
            return this.gameRepository.findAll();
        }

        query.select(game).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}