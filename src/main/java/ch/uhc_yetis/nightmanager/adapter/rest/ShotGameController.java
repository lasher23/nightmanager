package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.ShotGameService;
import ch.uhc_yetis.nightmanager.domain.model.ShotGame;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shots")
public class ShotGameController {
    private final ShotGameService shotGameService;

    public ShotGameController(ShotGameService shotGameService) {
        this.shotGameService = shotGameService;
    }

    @GetMapping("/ranking")
    public List<ShotGame> getRanking() {
        return this.shotGameService.getRanking();
    }

    @PostMapping("/{teamId}/increment")
    public ResponseEntity<ShotGame> incrementShots(@PathVariable long teamId, @RequestBody(required = false) ShotIncrementRequest request) {
        int count = (request != null && request.getCount() != null) ? request.getCount() : 1;
        ShotGame updated = this.shotGameService.incrementShotsForTeam(teamId, count);
        return ResponseEntity.ok(updated);
    }
}
