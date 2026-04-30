package ch.uhc_yetis.nightmanager.application;

import java.util.List;

/**
 * Request body for the interactive category generation endpoint.
 * Each {@link CategoryAssignment} maps a proposed category name
 * to the IDs of the RegistrationRequests that should become teams within that category.
 */
public class GenerationRequest {

    private List<CategoryAssignment> categories;

    public List<CategoryAssignment> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryAssignment> categories) {
        this.categories = categories;
    }

    public static class CategoryAssignment {
        private String name;
        private List<Long> requestIds;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Long> getRequestIds() {
            return requestIds;
        }

        public void setRequestIds(List<Long> requestIds) {
            this.requestIds = requestIds;
        }
    }
}
