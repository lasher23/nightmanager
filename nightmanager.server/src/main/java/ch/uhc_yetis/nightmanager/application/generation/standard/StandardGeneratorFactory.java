package ch.uhc_yetis.nightmanager.application.generation.standard;

import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.GeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.NullGenerator;
import ch.uhc_yetis.nightmanager.domain.model.Category;

public class StandardGeneratorFactory implements GeneratorFactory {
    @Override
    public Generator createGenerator(Category category) {
        switch (category.getState()) {
            case GROUP_PHASE:
                return new StandardSemiFinalGenerator();
            case SEMI_FINAL:
                return new StandardFinalGenerator();
            case FINAL:
                return new StandardFinishTableGenerator();
            case FINISHED:
            default:
                return new NullGenerator();
        }
    }
}
