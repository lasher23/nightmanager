package ch.uhc_yetis.nightmanager.application.simulation;

import ch.uhc_yetis.nightmanager.application.CustomException;
import ch.uhc_yetis.nightmanager.application.Status;
import ch.uhc_yetis.nightmanager.application.simulation.dto.SimulationCategoryDto;
import ch.uhc_yetis.nightmanager.application.simulation.dto.SimulationCategorySpec;
import ch.uhc_yetis.nightmanager.application.simulation.dto.SimulationTournamentSummary;
import ch.uhc_yetis.nightmanager.domain.model.*;
import ch.uhc_yetis.nightmanager.domain.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Backs the "simulation" walkthrough: quickly scaffolds a throwaway tournament
 * with categories and real (non-placeholder) teams of a chosen size, so the
 * game-generation feature can be exercised end to end without going through
 * the real registration flow. Everything created here is tagged with
 * {@link Tournament#isSimulation()} and can be wiped again in one go.
 */
@Service
public class SimulationService {

    private final TournamentRepository tournamentRepository;
    private final CategoryRepository categoryRepository;
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;
    private final GameGenerationProposalRepository proposalRepository;

    public SimulationService(TournamentRepository tournamentRepository,
                              CategoryRepository categoryRepository,
                              TeamRepository teamRepository,
                              GameRepository gameRepository,
                              GameGenerationProposalRepository proposalRepository) {
        this.tournamentRepository = tournamentRepository;
        this.categoryRepository = categoryRepository;
        this.teamRepository = teamRepository;
        this.gameRepository = gameRepository;
        this.proposalRepository = proposalRepository;
    }

    public List<SimulationTournamentSummary> listTournaments() {
        return this.tournamentRepository.findBySimulationTrueOrderByIdDesc().stream()
                .map(tournament -> {
                    SimulationTournamentSummary summary = new SimulationTournamentSummary();
                    summary.setId(tournament.getId());
                    summary.setName(tournament.getName());
                    summary.setCategoryCount(this.categoryRepository.findByTournament(tournament).size());
                    return summary;
                })
                .collect(Collectors.toList());
    }

    public Tournament createTournament(String name) {
        Tournament tournament = new Tournament();
        tournament.setName(name != null && !name.isBlank() ? name : "Simulation");
        tournament.setState(TournamentState.DRAFT);
        tournament.setSimulation(true);
        return this.tournamentRepository.save(tournament);
    }

    public List<SimulationCategoryDto> getCategories(long tournamentId) {
        Tournament tournament = findSimulationTournament(tournamentId);
        return this.categoryRepository.findByTournament(tournament).stream()
                .filter(category -> category.getParentCategory() == null)
                .sorted(Comparator.comparingLong(Category::getId))
                .map(category -> {
                    SimulationCategoryDto dto = new SimulationCategoryDto();
                    dto.setId(category.getId());
                    dto.setName(category.getName());
                    dto.setType(category.getType());
                    dto.setTeamCount(this.teamRepository.findByCategory(category).size());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SimulationCategoryDto> addCategories(long tournamentId, List<SimulationCategorySpec> specs) {
        Tournament tournament = findSimulationTournament(tournamentId);
        if (specs == null || specs.isEmpty()) {
            throw new CustomException("Keine Kategorien angegeben", Status.NOT_FOUND);
        }
        List<SimulationCategoryDto> created = new ArrayList<>();
        for (SimulationCategorySpec spec : specs) {
            Category category = new Category();
            category.setName(spec.getName());
            category.setType(spec.getType());
            category.setState(CategoryState.GROUP_PHASE);
            category.setTournament(tournament);
            category.setShowOnDisplay(true);
            Category saved = this.categoryRepository.save(category);

            for (int i = 1; i <= spec.getTeamCount(); i++) {
                Team team = new Team();
                team.setName(spec.getName() + " Team " + i);
                team.setCategory(saved);
                team.setPlaceholder(false);
                team.setRank(0);
                this.teamRepository.save(team);
            }

            SimulationCategoryDto dto = new SimulationCategoryDto();
            dto.setId(saved.getId());
            dto.setName(saved.getName());
            dto.setType(saved.getType());
            dto.setTeamCount(spec.getTeamCount());
            created.add(dto);
        }
        return created;
    }

    @Transactional
    public void deleteTournament(long tournamentId) {
        Tournament tournament = findSimulationTournament(tournamentId);
        this.proposalRepository.deleteAll(this.proposalRepository.findByTournamentOrderByCreatedAtDesc(tournament));

        List<Category> categories = this.categoryRepository.findByTournament(tournament);
        categories.stream().filter(c -> c.getParentCategory() != null).forEach(this::deleteCategoryCascade);
        categories.stream().filter(c -> c.getParentCategory() == null).forEach(this::deleteCategoryCascade);

        this.tournamentRepository.delete(tournament);
    }

    private void deleteCategoryCascade(Category category) {
        this.gameRepository.deleteAll(this.gameRepository.findByCategory(category));
        this.teamRepository.deleteAll(this.teamRepository.findByCategory(category));
        this.categoryRepository.delete(category);
    }

    private Tournament findSimulationTournament(long tournamentId) {
        Tournament tournament = this.tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException("Turnier mit id " + tournamentId + " nicht gefunden", Status.NOT_FOUND));
        if (!tournament.isSimulation()) {
            throw new CustomException("Turnier " + tournamentId + " ist kein Simulations-Turnier", Status.NOT_FOUND);
        }
        return tournament;
    }
}
