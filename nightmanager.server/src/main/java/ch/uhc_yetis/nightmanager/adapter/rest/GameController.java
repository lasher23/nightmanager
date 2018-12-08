package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.GameRequestParams;
import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAll(GameRequestParams requestPrams) {
        return this.gameService.getAll(requestPrams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable long id) {
        Optional<Game> game = this.gameService.getById(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/complete")
    public Game completeGame(@RequestBody Game game) {
        return this.gameService.complete(game);
    }

    @PostMapping("/reset")
    public Game resetGame(@RequestBody Game game) {
        return this.gameService.reset(game);
    }
}
