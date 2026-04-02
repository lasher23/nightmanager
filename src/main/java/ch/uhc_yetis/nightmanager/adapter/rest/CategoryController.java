package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.CategoryService;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.CATEGORY_LIST + "')")
    public List<Category> getAll(@RequestParam(required = false) Long tournamentId) {
        if (tournamentId != null) {
            return this.categoryService.findByTournamentId(tournamentId);
        }
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.CATEGORY_GET + "')")
    public Category getById(@PathVariable long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.CATEGORY_CREATE + "')")
    public Category saveNewCategory(@RequestBody Category category) {
        return this.categoryService.createNewCategory(category);
    }
}
