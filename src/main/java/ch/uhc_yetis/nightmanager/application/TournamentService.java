package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Tournament;
import ch.uhc_yetis.nightmanager.domain.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> findAll() {
        return this.tournamentRepository.findAll();
    }

    public Tournament findById(long id) {
        return this.tournamentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Turnier mit id " + id + " nicht gefunden", Status.NOT_FOUND));
    }

    public Tournament create(Tournament tournament) {
        tournament.setId(null);
        return this.tournamentRepository.save(tournament);
    }

    public Tournament save(Tournament tournament) {
        return this.tournamentRepository.save(tournament);
    }

    public void delete(long id) {
        this.tournamentRepository.deleteById(id);
    }
}
