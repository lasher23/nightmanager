package ch.uhc_yetis.nightmanager.application.generation.doublecategories;

import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.GeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.NullGenerator;
import ch.uhc_yetis.nightmanager.application.generation.single.SingleCategoryFinalGenerator;
import ch.uhc_yetis.nightmanager.application.generation.single.SingleCategoryFinishTableGenerator;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.stereotype.Component;

@Component
public class DoubleCategoriesGeneratorFactory implements GeneratorFactory {

    private DoubleCategoriesSemiFinalGenerator splitCategoriesSemiFinalGenerator;
    private Generator standardFinalGenerator;
    private Generator standardFinishTableGenerator;

    public DoubleCategoriesGeneratorFactory(DoubleCategoriesSemiFinalGenerator splitCategoriesSemiFinalGenerator,
                                            SingleCategoryFinalGenerator singleCategoryFinalGenerator,
                                            SingleCategoryFinishTableGenerator singleCategoryFinishTableGenerator) {
        this.splitCategoriesSemiFinalGenerator = splitCategoriesSemiFinalGenerator;
        this.standardFinalGenerator = singleCategoryFinalGenerator;
        this.standardFinishTableGenerator = singleCategoryFinishTableGenerator;
    }

    @Override
    public Generator createGenerator(Category category) {
        switch (category.getState()) {
            case DISABLED:
                return this.splitCategoriesSemiFinalGenerator;
            case SEMI_FINAL:
                return this.standardFinalGenerator;
            case FINAL:
                return this.standardFinishTableGenerator;
            default:
                return new NullGenerator();
        }
    }
}
