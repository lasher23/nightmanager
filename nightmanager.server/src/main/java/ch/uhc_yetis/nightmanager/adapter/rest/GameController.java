package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAll(@RequestParam(name = "category", required = false) Long categoryId, @RequestParam(name = "hall", required = false) Long hallId) {
        if (categoryId != null && hallId != null) {
            return this.gameService.getAllByHallAndCategory(hallId, categoryId);
        } else if (categoryId != null && hallId == null) {
            return this.gameService.getAllByCategory(categoryId);
        } else if (categoryId == null && hallId != null) {
            return this.gameService.getAllByHall(hallId);
        } else {
            return this.gameService.getAll();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable long id) {
        Optional<Game> game = this.gameService.getById(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Game saveNewGame(Game game) {
        return this.gameService.createNewGame(game);
    }

    @PostMapping("/complete")
    public Game completeGame(@RequestBody Game game) {
        return this.gameService.complete(game);
    }
}
