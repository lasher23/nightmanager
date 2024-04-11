package ch.uhc_yetis.nightmanager.application.generation;

import ch.uhc_yetis.nightmanager.application.GameService;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class GenerationService {

    private Map<Predicate<Category>, Generator> generators = new HashMap<>();
    private GameService gameService;
    private GeneratorFactoryProducer generatorFactoryProducer;

    public GenerationService(GameService gameService,
                             GeneratorFactoryProducer generatorFactoryProducer) {
        this.gameService = gameService;
        this.generatorFactoryProducer = generatorFactoryProducer;
    }

    public void generateFromCategory(Category category) {
        this.generatorFactoryProducer.getFactory(category).createGenerator(category).generate(category);
    }
}
