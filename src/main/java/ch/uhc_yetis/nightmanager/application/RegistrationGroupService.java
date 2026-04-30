package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.RegistrationGroup;
import ch.uhc_yetis.nightmanager.domain.model.Tournament;
import ch.uhc_yetis.nightmanager.domain.model.TournamentState;
import ch.uhc_yetis.nightmanager.domain.repository.RegistrationGroupRepository;
import ch.uhc_yetis.nightmanager.domain.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationGroupService {

    private final RegistrationGroupRepository registrationGroupRepository;
    private final TournamentRepository tournamentRepository;

    public RegistrationGroupService(RegistrationGroupRepository registrationGroupRepository,
                                    TournamentRepository tournamentRepository) {
        this.registrationGroupRepository = registrationGroupRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public List<RegistrationGroup> findByTournamentId(Long tournamentId) {
        Tournament tournament = findTournament(tournamentId);
        return registrationGroupRepository.findByTournament(tournament);
    }

    public RegistrationGroup findById(Long id) {
        return registrationGroupRepository.findById(id)
                .orElseThrow(() -> new CustomException("Anmeldegruppe mit id " + id + " nicht gefunden", Status.NOT_FOUND));
    }

    public RegistrationGroup create(Long tournamentId, RegistrationGroup group) {
        Tournament tournament = findTournament(tournamentId);
        if (tournament.getState() != TournamentState.DRAFT) {
            throw new CustomException("Anmeldegruppen können nur im Status DRAFT hinzugefügt werden", Status.ALREADY_EXISTS);
        }
        group.setId(null);
        group.setTournament(tournament);
        return registrationGroupRepository.save(group);
    }

    public RegistrationGroup save(RegistrationGroup group) {
        return registrationGroupRepository.save(group);
    }

    public void delete(Long id) {
        registrationGroupRepository.deleteById(id);
    }

    private Tournament findTournament(Long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException("Turnier mit id " + tournamentId + " nicht gefunden", Status.NOT_FOUND));
    }
}
