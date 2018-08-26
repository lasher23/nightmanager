package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final HallService hallService;
    private final CategoryService categoryService;

    public GameService(GameRepository gameRepository, HallService hallService, CategoryService categoryService) {
        this.gameRepository = gameRepository;
        this.hallService = hallService;
        this.categoryService = categoryService;
    }

    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    public Game createNewGame(Game game) {
        game.setId(0);
        return this.gameRepository.save(game);
    }


    public Optional<Game> getById(long id) {
        return this.gameRepository.findById(id);
    }

    public List<Game> getAllByHall(long hallId) {
        return this.gameRepository.findByHall(this.hallService.getById(hallId));
    }

    public List<Game> getAllByCategory(long categoryId) {
        return this.gameRepository.findByCategory(this.categoryService.findById(categoryId));
    }

    public List<Game> getAllByHallAndCategory(long hallId, long categoryId) {
        return this.gameRepository.findByHallAndCategory(this.hallService.getById(hallId), this.categoryService.findById(categoryId));
    }

    public Game complete(Game game) {
        if (this.gameRepository.existsById(game.getId())) {
            return this.gameRepository.save(game);
        }
        throw new CustomException("Spiel " + game.getId() + "exisiert nicht", Status.NOT_FOUND);
    }
}
