package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.GameRequestParams;
import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.Role;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAll(GameRequestParams requestPrams) {
        return this.gameService.getAll(requestPrams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable long id) {
        return this.gameService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.REFEREE + "') or hasAuthority('" + RoleConstants.ADMIN + "')")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        return this.gameService.getById(game.getId())
                .map(x -> this.gameService.save(game))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PatchMapping("/{id}/live")
    @PreAuthorize("hasAuthority('" + RoleConstants.REFEREE + "') or hasAuthority('" + RoleConstants.ADMIN + "')")
    public ResponseEntity<Game> updateGamePartial(@PathVariable("id") long id, @RequestBody Game game) {
        return this.gameService.getById(id)
                .map(x -> this.gameService.updateLive(x, game.isLive()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/next")
    public ResponseEntity<Game> getNextGame(@PathVariable("id") long id) {
        return this.gameService.getById(id)
                .map(this.gameService::getNextGame)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/complete")
    @PreAuthorize("hasAuthority('" + RoleConstants.REFEREE + "') or hasAuthority('" + RoleConstants.ADMIN + "')")
    public Game completeGame(@RequestBody Game game) {
        return this.gameService.complete(game);
    }

    @PostMapping("/reset")
    @PreAuthorize("hasAuthority('" + RoleConstants.ADMIN + "')")
    public ResponseEntity<Game> resetGame(@RequestBody Game game) {
        return this.gameService.reset(game).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping("/notify")
    @PreAuthorize("hasAuthority('" + RoleConstants.ADMIN + "')")
    public ResponseEntity<Game> notifyGame(@RequestBody Game game) {
        return this.gameService.notify(game).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }
}
