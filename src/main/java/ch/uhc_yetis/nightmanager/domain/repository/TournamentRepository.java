package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findBySimulationTrueOrderByIdDesc();
}
