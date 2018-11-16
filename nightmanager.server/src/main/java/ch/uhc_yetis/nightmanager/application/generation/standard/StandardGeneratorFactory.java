package ch.uhc_yetis.nightmanager.application.generation.standard;

import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.GeneratorFactory;
import ch.uhc_yetis.nightmanager.application.generation.NullGenerator;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.stereotype.Service;

@Service
public class StandardGeneratorFactory implements GeneratorFactory {
    private StandardSemiFinalGenerator standardSemiFinalGenerator;
    private StandardFinalGenerator standardFinalGenerator;
    private StandardFinishTableGenerator standardFinishTableGenerator;

    public StandardGeneratorFactory(StandardSemiFinalGenerator standardSemiFinalGenerator, StandardFinalGenerator standardFinalGenerator, StandardFinishTableGenerator standardFinishTableGenerator) {
        this.standardSemiFinalGenerator = standardSemiFinalGenerator;
        this.standardFinalGenerator = standardFinalGenerator;
        this.standardFinishTableGenerator = standardFinishTableGenerator;
    }

    @Override
    public Generator createGenerator(Category category) {
        switch (category.getState()) {
            case GROUP_PHASE:
                return this.standardSemiFinalGenerator;
            case SEMI_FINAL:
                return this.standardFinalGenerator;
            case FINAL:
                return this.standardFinishTableGenerator;
            case FINISHED:
            default:
                return new NullGenerator();
        }
    }
}
