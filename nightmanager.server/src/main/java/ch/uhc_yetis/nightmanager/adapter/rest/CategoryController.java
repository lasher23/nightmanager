package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.CategoryService;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.ADMIN + "')")
    public Category saveNewCategory(@RequestBody Category category) {
        return this.categoryService.createNewCategory(category);
    }
}
