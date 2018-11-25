package ch.uhc_yetis.nightmanager.application.generation.splitcategories;

import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.GeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.standard.StandardFinalGenerator;
import ch.uhc_yetis.nightmanager.application.generation.standard.StandardFinishTableGenerator;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.stereotype.Component;

@Component
public class SplitCategoriesGeneratorFactory implements GeneratorFactory {

    private SplitCategoriesSemiFinalGenerator splitCategoriesSemiFinalGenerator;
    private Generator standardFinalGenerator;
    private Generator standardFinishTableGenerator;

    public SplitCategoriesGeneratorFactory(SplitCategoriesSemiFinalGenerator splitCategoriesSemiFinalGenerator,
                                           StandardFinalGenerator standardFinalGenerator,
                                           StandardFinishTableGenerator standardFinishTableGenerator) {
        this.splitCategoriesSemiFinalGenerator = splitCategoriesSemiFinalGenerator;
        this.standardFinalGenerator = standardFinalGenerator;
        this.standardFinishTableGenerator = standardFinishTableGenerator;
    }

    @Override
    public Generator createGenerator(Category category) {
        switch (category.getState()) {
            case SEMI_FINAL:
                return this.splitCategoriesSemiFinalGenerator;
            case FINAL:
                return this.standardFinalGenerator;
            case FINISHED:
                return this.standardFinishTableGenerator;
            default:
                return null;
        }
    }
}
