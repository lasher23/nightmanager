package ch.uhc_yetis.nightmanager.adapter.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.uhc_yetis.nightmanager.application.GameRequestParams;
import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_LIST + "')")
    public List<Game> getAll(GameRequestParams requestPrams) {
        return this.gameService.getAll(requestPrams);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_GET + "')")
    public ResponseEntity<Game> getById(@PathVariable long id) {
        return this.gameService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_UPDATE + "')")
    public ResponseEntity<Game> updateGame(@RequestBody Game game) {
        return this.gameService.getById(game.getId())
                .map(x -> this.gameService.save(game))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PatchMapping("/{id}/live")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_UPDATE + "')")
    public ResponseEntity<Game> updateGamePartial(@PathVariable("id") long id, @RequestBody Game game) {
        return this.gameService.getById(id)
                .map(x -> this.gameService.updateLive(x, game.isLive()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/next")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_GET + "')")
    public ResponseEntity<Game> getNextGame(@PathVariable("id") long id) {
        return this.gameService.getById(id)
                .map(this.gameService::getNextGame)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/complete")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_UPDATE + "')")
    public Game completeGame(@RequestBody Game game) {
        return this.gameService.complete(game);
    }

    @PostMapping("/reset")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_MANAGE + "')")
    public ResponseEntity<Game> resetGame(@RequestBody Game game) {
        return this.gameService.reset(game).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }

    @PatchMapping("/swap")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_UPDATE + "')")
    public ResponseEntity<Game> swapTeams(@RequestBody Game game) {
        return this.gameService.swapTeams(game).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }

    @PostMapping("/notify")
    @PreAuthorize("hasAuthority('" + RoleConstants.GAME_NOTIFY + "')")
    public ResponseEntity<Game> notifyGame(@RequestBody Game game) {
        return this.gameService.notify(game).map(ResponseEntity::ok).orElseGet(ResponseEntity.notFound()::build);
    }
}
