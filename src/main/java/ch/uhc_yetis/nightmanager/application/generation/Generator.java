package ch.uhc_yetis.nightmanager.application.generation;

import ch.uhc_yetis.nightmanager.domain.model.Category;

public interface Generator {
    void generate(Category category);
}
