package ch.uhc_yetis.nightmanager.application.generation.yetiscup;

import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.GeneratorFactory;
import ch.uhc_yetis.nightmanager.domain.model.Category;

public class YetisCupGeneratorFactory implements GeneratorFactory {
    @Override
    public Generator createGenerator(Category category) {
        switch (category.getState()) {
            case CROKI_FIRST:
                return new YetisCupCrokiGenerator();
            case GROUP_PHASE:
            case SEMI_FINAL:
            case FINAL:
            case FINISHED:
        }
        return null;
    }
}
