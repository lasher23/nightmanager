package ch.uhc_yetis.nightmanager.application.generation;

import ch.uhc_yetis.nightmanager.domain.model.Category;

public class NullGeneratorFactory implements GeneratorFactory {
    @Override
    public Generator createGenerator(Category category) {
        return null;
    }
}
