package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Category findById(long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new CustomException("Kategorie mit id " + id + "nicht gefunden", Status.NOT_FOUND));
    }

    public Category createNewCategory(Category category) {
        category.setId(0);
        return this.categoryRepository.save(category);
    }

    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    public List<Category> findByParentCategory(Category category) {
        return this.categoryRepository.findByParentCategory(category);
    }
}
