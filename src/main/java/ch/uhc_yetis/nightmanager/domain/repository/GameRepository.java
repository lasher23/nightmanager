package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.*;

import java.time.LocalDateTime;

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

    @Query("select g from Game g where (g.teamGuest=:teamGuest or g.teamHome=:teamHome) and g.type = :type and g.state = :state")
    List<Game> findAllByTeamGuestOrTeamHomeAndTypeAndState(@Param("teamGuest") Team teamGuest,
                                                           @Param("teamHome") Team teamHome, @Param("type") GameType type, @Param("state") GameState state);

    List<Game> findAllByCategoryAndType(Category category, GameType type);

    @Query("select g from Game g where (teamHome=:team1 or teamGuest=:team1) and (teamHome=:team2 or teamGuest=:team2) and type = :type")
    Optional<Game> findByTwoTeamsAndType(@Param("team1") Team team1, @Param("team2") Team team2,
                                         @Param("type") GameType type);

    List<Game> findByHallAndLive(Hall hall, boolean live);

    Game findFirstByStartDateGreaterThanAndHallOrderByStartDate(LocalDateTime startDate, Hall hall);

    List<Game> findAllByStartDateBetween(LocalDateTime from, LocalDateTime to);

    List<Game> findAllByTeamHomeOrTeamGuestOrderByStartDate(Team teamHome, Team teamGuest);

    @Query("select g from Game g where (teamHome=:team or teamGuest=:team) and type = :type")
    List<Game> findAllByTeamAndType(@Param("team") Team team, @Param("type") GameType type);

    @Query("select g from Game g where (teamHome=:team or teamGuest=:team) and category = :category order by startDate")
    List<Game> findAllByTeamAndCategoryOrderByStartDate(@Param("team") Team team, @Param("category") Category category);
}
