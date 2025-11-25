package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.ShotGame;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import ch.uhc_yetis.nightmanager.domain.repository.ShotGameRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShotGameService {
    private final ShotGameRepository shotGameRepository;
    private final TeamService teamService;
    private final SimpMessagingTemplate template;

    public ShotGameService(ShotGameRepository shotGameRepository, TeamService teamService, SimpMessagingTemplate template) {
        this.shotGameRepository = shotGameRepository;
        this.teamService = teamService;
        this.template = template;
    }

    public List<ShotGame> getRanking() {
        return this.shotGameRepository.findAllByOrderByShotsDesc();
    }

    public ShotGame incrementShotsForTeam(long teamId) {
        return this.incrementShotsForTeam(teamId, 1);
    }

    public ShotGame incrementShotsForTeam(long teamId, int count) {
        Team team = this.teamService.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team not found: " + teamId));
        ShotGame shotGame = this.shotGameRepository.findByTeam(team).orElseGet(() -> {
            ShotGame s = new ShotGame();
            s.setTeam(team);
            s.setShots(0);
            return s;
        });
        int newShots = shotGame.getShots() + count;
        if (newShots < 0) {
            newShots = 0;
        }
        shotGame.setShots(newShots);
        ShotGame saved = this.shotGameRepository.save(shotGame);
        // notify clients of shot change
        this.template.convertAndSend("/topic/nightmanager-shot-change", "{\"status\":\"ShotChanged\"}");
        return saved;
    }
}
