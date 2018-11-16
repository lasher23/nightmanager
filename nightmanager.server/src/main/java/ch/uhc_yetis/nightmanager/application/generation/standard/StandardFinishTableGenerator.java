package ch.uhc_yetis.nightmanager.application.generation.standard;

import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.model.Game;
import ch.uhc_yetis.nightmanager.domain.model.GameType;
import ch.uhc_yetis.nightmanager.domain.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StandardFinishTableGenerator implements Generator {
    @Autowired
    private GameService gameService;
    @Autowired
    private TeamService teamService;

    public StandardFinishTableGenerator(GameService gameService, TeamService teamService) {
        this.gameService = gameService;
        this.teamService = teamService;
    }

    @Override
    public void generate(Category category) {
        List<Game> finals = this.gameService.getAllGamesByCategoryAndType(category, GameType.FINAL).stream().sorted(Comparator.comparingLong(x -> x.getHall().getId())).collect(Collectors.toList());
        this.setFromBigFinal(category, finals);
        this.setFromLittleFinal(category, finals);
    }

    private void setFromBigFinal(Category category, List<Game> finals) {
        Team winnerBigFinal = this.gameService.getWinner(finals.get(0)).orElseThrow(() -> new GenerationException(category, "Keine Unentschiden im Finale erlaubt"));
        Team looserBigFinal = this.gameService.getLooser(finals.get(0)).orElseThrow(() -> new GenerationException(category, "Keine Unentschiden im Finale erlaubt"));
        winnerBigFinal.setRank(1);
        looserBigFinal.setRank(2);
        this.teamService.save(winnerBigFinal);
        this.teamService.save(looserBigFinal);

    }

    private void setFromLittleFinal(Category category, List<Game> finals) {
        Team winnerLittleFinal = this.gameService.getWinner(finals.get(1)).orElseThrow(() -> new GenerationException(category, "Keine Unentschiden im Finale erlaubt"));
        Team looserLittleFinal = this.gameService.getLooser(finals.get(1)).orElseThrow(() -> new GenerationException(category, "Keine Unentschiden im Finale erlaubt"));
        winnerLittleFinal.setRank(3);
        looserLittleFinal.setRank(4);
        this.teamService.save(winnerLittleFinal);
        this.teamService.save(looserLittleFinal);
    }
}
