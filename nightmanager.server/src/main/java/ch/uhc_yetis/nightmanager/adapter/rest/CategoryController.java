package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.CategoryService;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping
    public Category saveNewCategory(@RequestBody Category category) {
        return this.categoryService.createNewCategory(category);
    }
}
