package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.ShotGame;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface ShotGameRepository extends JpaRepository<ShotGame, Long> {
    Optional<ShotGame> findByTeam(Team team);

    List<ShotGame> findAllByOrderByShotsDesc();
}
