package ch.uhc_yetis.nightmanager.application.generation.yetiscup;

import ch.uhc_yetis.nightmanager.application.generation.Generator;
import ch.uhc_yetis.nightmanager.application.generation.GeneratorFactory;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.stereotype.Component;

@Component
public class YetisCupGeneratorFactory implements GeneratorFactory {
    private YetisCupGenerator yetisCupGenerator;

    public YetisCupGeneratorFactory(YetisCupGenerator yetisCupGenerator) {
        this.yetisCupGenerator = yetisCupGenerator;
    }

    @Override
    public Generator createGenerator(Category category) {
        switch (category.getState()) {
            case CROKI_FIRST:
                return this.yetisCupGenerator;
            case GROUP_PHASE:
            case SEMI_FINAL:
            case FINAL:
            case FINISHED:
            default:
                return null;
        }
    }
}
