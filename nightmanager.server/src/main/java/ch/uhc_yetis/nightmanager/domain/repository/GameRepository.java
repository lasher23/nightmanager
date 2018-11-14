package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.application.TeamDto;
import ch.uhc_yetis.nightmanager.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {
    List<Game> findByHall(Hall hall);

    List<Game> findByCategory(Category category);

    List<Game> findByHallAndCategory(Hall hall, Category category);

    List<Game> findAllByTeamGuestOrTeamHome(Team teamGuest, Team teamHome);

    List<Game> findAllByTeamGuestOrTeamHomeAndType(Team teamGuest, Team teamHome, GameType type);

    List<Game> findAllByCategoryAndType(Category category, GameType type);

    @Query("select * from games where (fk_team_guest=:team1 or fk_team_home=:team1) and (fk_team_guest=:team2 or fk_team_home=:team2) and type = :type")
    Optional<Game> findByTwoTeamsAndType(TeamDto team1, TeamDto team2, GameType type);
}
