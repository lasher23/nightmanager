package ch.uhc_yetis.nightmanager.application.generation;

import ch.uhc_yetis.nightmanager.application.generation.splitcategories.SplitCategoriesGeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.standard.StandardGeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.yetiscup.YetisCupGeneratorFactory;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.stereotype.Service;

@Service
public class GeneratorFactoryProducer {

    private StandardGeneratorFactory standardGeneratorFactory;

    public GeneratorFactoryProducer(StandardGeneratorFactory standardGeneratorFactory) {
        this.standardGeneratorFactory = standardGeneratorFactory;
    }

    public GeneratorFactory getFactory(Category category) {
        switch (category.getType()) {
            case SPLIT_CATEGORIES:
                return new SplitCategoriesGeneratorFactory();
            case YETIS_CUP:
                return new YetisCupGeneratorFactory();
            case STANDARD:
                return this.standardGeneratorFactory;
            default:
                return new NullGeneratorFactory();
        }
    }
}
