package ch.uhc_yetis.nightmanager.application.generation.standard;

import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.GameType;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StandardFinishTableGenerator implements Generator {
    @Autowired
    private GameService gameService;

    @Override
    public void generate(Category category) {
        List<Game> finals = this.gameService.getAllGamesByCategoryAndType(category, GameType.FINAL).stream().sorted(Comparator.comparingLong(x -> x.getHall().getId())).collect(Collectors.toList());
        Team winner = gameService.getWinner(finals.get(0)).orElseThrow(() -> new GenerationException(category, "Keine Unentschiden im Finale erlaubt"));
        gameService.getLooser(finals.get(1)).orElseThrow(()-> new GenerationException(category, "Keine Unentschiden im Finale erlaubt"));
    }
}
