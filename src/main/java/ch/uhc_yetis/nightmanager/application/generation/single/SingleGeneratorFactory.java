package ch.uhc_yetis.nightmanager.application.generation.single;

import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.GeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.NullGenerator;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.stereotype.Service;

@Service
public class SingleGeneratorFactory implements GeneratorFactory {
    private SingleSemiFinalGenerator singleSemiFinalGenerator;
    private SingleCategoryFinalGenerator singleCategoryFinalGenerator;
    private SingleCategoryFinishTableGenerator singleCategoryFinishTableGenerator;

    public SingleGeneratorFactory(SingleSemiFinalGenerator singleSemiFinalGenerator, SingleCategoryFinalGenerator singleCategoryFinalGenerator, SingleCategoryFinishTableGenerator singleCategoryFinishTableGenerator) {
        this.singleSemiFinalGenerator = singleSemiFinalGenerator;
        this.singleCategoryFinalGenerator = singleCategoryFinalGenerator;
        this.singleCategoryFinishTableGenerator = singleCategoryFinishTableGenerator;
    }

    @Override
    public Generator createGenerator(Category category) {
        switch (category.getState()) {
            case GROUP_PHASE:
                return this.singleSemiFinalGenerator;
            case SEMI_FINAL:
                return this.singleCategoryFinalGenerator;
            case FINAL:
                return this.singleCategoryFinishTableGenerator;
            case FINISHED:
            default:
                return new NullGenerator();
        }
    }
}
