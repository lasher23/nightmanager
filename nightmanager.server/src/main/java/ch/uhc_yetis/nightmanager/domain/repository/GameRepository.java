package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {
    List<Game> findByHall(Hall hall);

    List<Game> findByCategory(Category category);

    List<Game> findByHallAndCategory(Hall hall, Category category);

    List<Game> findAllByTeamGuestOrTeamHome(Team teamGuest, Team teamHome);

    List<Game> findAllByTeamGuestOrTeamHomeAndType(Team teamGuest, Team teamHome, GameType type);
}
