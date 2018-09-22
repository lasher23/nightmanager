package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.Hall;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {
    List<Game> findByHall(Hall hall);

    List<Game> findByCategory(Category category);

    List<Game> findByHallAndCategory(Hall hall, Category category);

    List<Game> findAllByTeamGuestOrTeamHome(Team teamGuest, Team teamHome);
}
