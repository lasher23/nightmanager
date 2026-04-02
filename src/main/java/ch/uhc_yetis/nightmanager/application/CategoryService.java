package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.model.Tournament;
import ch.uhc_yetis.nightmanager.domain.repository.CategoryRepository;
import ch.uhc_yetis.nightmanager.domain.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private TournamentRepository tournamentRepository;

    public CategoryService(CategoryRepository categoryRepository, TournamentRepository tournamentRepository) {
        this.categoryRepository = categoryRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public List<Category> findByTournamentId(long tournamentId) {
        Tournament tournament = this.tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException("Turnier mit id " + tournamentId + " nicht gefunden", Status.NOT_FOUND));
        return this.categoryRepository.findByTournament(tournament);
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
