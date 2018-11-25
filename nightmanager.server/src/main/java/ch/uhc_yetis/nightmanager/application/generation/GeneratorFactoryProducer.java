package ch.uhc_yetis.nightmanager.application.generation;

import ch.uhc_yetis.nightmanager.application.generation.splitcategories.SplitCategoriesGeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.standard.StandardGeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.yetiscup.YetisCupGeneratorFactory;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.stereotype.Service;

@Service
public class GeneratorFactoryProducer {

    private StandardGeneratorFactory standardGeneratorFactory;
    private SplitCategoriesGeneratorFactory splitCategoriesGeneratorFactory;
    private YetisCupGeneratorFactory yetisCupGeneratorFactory;

    public GeneratorFactoryProducer(StandardGeneratorFactory standardGeneratorFactory, SplitCategoriesGeneratorFactory splitCategoriesGeneratorFactory, YetisCupGeneratorFactory yetisCupGeneratorFactory) {
        this.standardGeneratorFactory = standardGeneratorFactory;
        this.splitCategoriesGeneratorFactory = splitCategoriesGeneratorFactory;
        this.yetisCupGeneratorFactory = yetisCupGeneratorFactory;
    }

    public GeneratorFactory getFactory(Category category) {
        switch (category.getType()) {
            case SPLIT_CATEGORIES:
                return this.splitCategoriesGeneratorFactory;
            case YETIS_CUP:
                return this.yetisCupGeneratorFactory;
            case STANDARD:
                return this.standardGeneratorFactory;
            default:
                return new NullGeneratorFactory();
        }
    }
}
