package ch.uhc_yetis.nightmanager.application.generation;

import ch.uhc_yetis.nightmanager.application.generation.doublecategories.DoubleCategoriesGeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.single.SingleGeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.yetiscup.YetisCupGeneratorFactory;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.stereotype.Service;

@Service
public class GeneratorFactoryProducer {

    private final SingleGeneratorFactory singleGeneratorFactory;
    private final DoubleCategoriesGeneratorFactory doubleCategoriesGeneratorFactory;
    private final YetisCupGeneratorFactory yetisCupGeneratorFactory;

    public GeneratorFactoryProducer(SingleGeneratorFactory singleGeneratorFactory, DoubleCategoriesGeneratorFactory doubleCategoriesGeneratorFactory, YetisCupGeneratorFactory yetisCupGeneratorFactory) {
        this.singleGeneratorFactory = singleGeneratorFactory;
        this.doubleCategoriesGeneratorFactory = doubleCategoriesGeneratorFactory;
        this.yetisCupGeneratorFactory = yetisCupGeneratorFactory;
    }

    public GeneratorFactory getFactory(Category category) {
        switch (category.getType()) {
            case DOUBLE_CATEGORIES:
                return this.doubleCategoriesGeneratorFactory;
            case YETIS_CUP:
                return this.yetisCupGeneratorFactory;
            case SINGLE_CATEGORY:
                return this.singleGeneratorFactory;
            default:
                return new NullGeneratorFactory();
        }
    }
}
