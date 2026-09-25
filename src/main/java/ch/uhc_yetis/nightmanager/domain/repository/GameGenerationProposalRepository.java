package ch.uhc_yetis.nightmanager.domain.repository;

import ch.uhc_yetis.nightmanager.domain.model.GameGenerationProposal;
import ch.uhc_yetis.nightmanager.domain.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameGenerationProposalRepository extends JpaRepository<GameGenerationProposal, Long> {
    List<GameGenerationProposal> findByTournamentOrderByCreatedAtDesc(Tournament tournament);
}
