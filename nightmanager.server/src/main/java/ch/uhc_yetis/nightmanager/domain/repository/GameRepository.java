package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {
    List<Game> findByHall(Hall hall);

    List<Game> findByCategory(Category category);

    List<Game> findByHallAndCategory(Hall hall, Category category);

    List<Game> findAllByTeamGuestOrTeamHome(Team teamGuest, Team teamHome);

    List<Game> findAllByTeamGuestOrTeamHomeAndType(Team teamGuest, Team teamHome, GameType type);

    List<Game> findAllByCategoryAndType(Category category, GameType type);

    @Query("select g from Game g where (teamHome=:team1 or teamGuest=:team1) and (teamHome=:team2 or teamGuest=:team2) and type = :type")
    Optional<Game> findByTwoTeamsAndType(@Param("team1") Team team1, @Param("team2") Team team2, @Param("type") GameType type);
}
