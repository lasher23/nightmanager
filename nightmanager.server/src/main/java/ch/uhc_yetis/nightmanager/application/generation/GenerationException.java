package ch.uhc_yetis.nightmanager.application.generation;

import ch.uhc_yetis.nightmanager.domain.model.Category;

public class GenerationException extends RuntimeException {
    private Category category;
    private String message;

    public GenerationException(Category category, String message) {
        this.category = category;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
