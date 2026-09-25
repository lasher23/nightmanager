package ch.uhc_yetis.nightmanager.application.gamegeneration;

import ch.uhc_yetis.nightmanager.application.CustomException;
import ch.uhc_yetis.nightmanager.application.Status;
import ch.uhc_yetis.nightmanager.application.gamegeneration.dto.*;
import ch.uhc_yetis.nightmanager.domain.model.*;
import ch.uhc_yetis.nightmanager.domain.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Builds and persists editable "game generation proposals": a full,
 * hall-by-hall schedule of group-stage and placeholder knockout games for a
 * selection of categories, computed up front (before any results exist) so it
 * can be reviewed, adjusted and only then committed into real Game/Team rows.
 */
@Service
public class GameGenerationService {

    private static final long GAME_DURATION_MILLIS = 10 * 60 * 1000L;

    private final GameGenerationProposalRepository proposalRepository;
    private final CategoryRepository categoryRepository;
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;
    private final HallRepository hallRepository;
    private final TournamentRepository tournamentRepository;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public GameGenerationService(GameGenerationProposalRepository proposalRepository,
                                  CategoryRepository categoryRepository,
                                  TeamRepository teamRepository,
                                  GameRepository gameRepository,
                                  HallRepository hallRepository,
                                  TournamentRepository tournamentRepository) {
        this.proposalRepository = proposalRepository;
        this.categoryRepository = categoryRepository;
        this.teamRepository = teamRepository;
        this.gameRepository = gameRepository;
        this.hallRepository = hallRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public List<Category> getEligibleCategories(long tournamentId) {
        Tournament tournament = findTournament(tournamentId);
        return this.categoryRepository.findByTournament(tournament).stream()
                .filter(category -> category.getParentCategory() == null)
                .filter(category -> category.getState() != CategoryState.DISABLED)
                .filter(category -> this.gameRepository.findByCategory(category).isEmpty())
                .collect(Collectors.toList());
    }

    public List<ProposalSummaryDto> listProposals(long tournamentId) {
        Tournament tournament = findTournament(tournamentId);
        return this.proposalRepository.findByTournamentOrderByCreatedAtDesc(tournament).stream()
                .map(this::toSummaryDto)
                .collect(Collectors.toList());
    }

    public GameGenerationProposalDto getProposal(long id) {
        GameGenerationProposal entity = findProposal(id);
        return toDto(entity, readPayload(entity));
    }

    public GameGenerationProposalDto propose(long tournamentId, ProposeRequest request) {
        Tournament tournament = findTournament(tournamentId);
        List<Hall> halls = this.hallRepository.findAll().stream()
                .sorted(Comparator.comparingLong(Hall::getId))
                .collect(Collectors.toList());
        if (halls.isEmpty()) {
            throw new CustomException("Keine Hallen vorhanden", Status.NOT_FOUND);
        }
        if (request.getCategoryIds() == null || request.getCategoryIds().isEmpty()) {
            throw new CustomException("Keine Kategorien ausgewählt", Status.NOT_FOUND);
        }
        List<Category> categories = request.getCategoryIds().stream()
                .map(id -> this.categoryRepository.findById(id)
                        .orElseThrow(() -> new CustomException("Kategorie mit id " + id + " nicht gefunden", Status.NOT_FOUND)))
                .collect(Collectors.toList());
        LocalDateTime startTime = request.getStartTime() != null ? request.getStartTime() : LocalDateTime.now();

        ProposalPayload payload = buildPayload(categories, halls, startTime);

        GameGenerationProposal entity = new GameGenerationProposal();
        entity.setTournament(tournament);
        entity.setName(request.getName() != null && !request.getName().isBlank()
                ? request.getName()
                : "Spielplan-Entwurf " + startTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        entity.setStartTime(startTime);
        entity.setStatus(ProposalStatus.DRAFT);
        entity.setPayload(writePayload(payload));
        GameGenerationProposal saved = this.proposalRepository.save(entity);
        return toDto(saved, payload);
    }

    public GameGenerationProposalDto updateProposal(long id, GameGenerationProposalDto dto) {
        GameGenerationProposal entity = findProposal(id);
        if (entity.getStatus() == ProposalStatus.COMMITTED) {
            throw new CustomException("Vorschlag wurde bereits umgesetzt und kann nicht mehr bearbeitet werden", Status.ALREADY_EXISTS);
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }
        if (dto.getStartTime() != null) {
            entity.setStartTime(dto.getStartTime());
        }
        ProposalPayload payload = new ProposalPayload();
        payload.setCategories(dto.getCategories() != null ? dto.getCategories() : new ArrayList<>());
        payload.setGames(dto.getGames() != null ? dto.getGames() : new ArrayList<>());
        entity.setPayload(writePayload(payload));
        entity.setUpdatedAt(Instant.now());
        GameGenerationProposal saved = this.proposalRepository.save(entity);
        return toDto(saved, payload);
    }

    public void deleteProposal(long id) {
        GameGenerationProposal entity = findProposal(id);
        this.proposalRepository.delete(entity);
    }

    public List<Category> commit(long id) {
        GameGenerationProposal entity = findProposal(id);
        if (entity.getStatus() == ProposalStatus.COMMITTED) {
            throw new CustomException("Vorschlag wurde bereits umgesetzt", Status.ALREADY_EXISTS);
        }
        ProposalPayload payload = readPayload(entity);

        Map<String, Long> categoryIdByRef = new HashMap<>();
        for (ProposedCategoryDto category : payload.getCategories()) {
            if (category.getExistingCategoryId() != null) {
                categoryIdByRef.put(category.getRef(), category.getExistingCategoryId());
            }
        }
        for (ProposedCategoryDto category : payload.getCategories()) {
            if (category.getExistingCategoryId() == null) {
                Category parent = category.getParentCategoryId() != null
                        ? this.categoryRepository.findById(category.getParentCategoryId())
                            .orElseThrow(() -> new CustomException("Übergeordnete Kategorie nicht gefunden", Status.NOT_FOUND))
                        : null;
                Category created = new Category();
                created.setName(category.getName());
                created.setType(category.getType());
                created.setState(CategoryState.SEMI_FINAL);
                created.setTournament(entity.getTournament());
                created.setParentCategory(parent);
                created.setShowOnDisplay(true);
                Category saved = this.categoryRepository.save(created);
                categoryIdByRef.put(category.getRef(), saved.getId());
            }
        }

        Map<String, Long> teamIdByRef = new HashMap<>();
        for (ProposedGameDto game : payload.getGames()) {
            resolveTeam(game.getTeamHome(), teamIdByRef, categoryIdByRef);
            resolveTeam(game.getTeamGuest(), teamIdByRef, categoryIdByRef);
        }

        for (ProposedGameDto proposedGame : payload.getGames()) {
            Long categoryId = categoryIdByRef.get(proposedGame.getCategoryRef());
            if (categoryId == null) {
                throw new CustomException("Kategorie-Referenz '" + proposedGame.getCategoryRef() + "' konnte nicht aufgelöst werden", Status.NOT_FOUND);
            }
            Game game = new Game();
            game.setCategory(this.categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CustomException("Kategorie nicht gefunden", Status.NOT_FOUND)));
            game.setHall(this.hallRepository.findById(proposedGame.getHallId())
                    .orElseThrow(() -> new CustomException("Halle nicht gefunden", Status.NOT_FOUND)));
            game.setStartDate(proposedGame.getStartDate());
            game.setDuration(GAME_DURATION_MILLIS);
            game.setType(proposedGame.getType());
            game.setState(GameState.OPEN);
            game.setPlaceholder(proposedGame.isPlaceholder());
            game.setTeamHome(this.teamRepository.findById(teamIdByRef.get(proposedGame.getTeamHome().getRef()))
                    .orElseThrow(() -> new CustomException("Team nicht gefunden", Status.NOT_FOUND)));
            game.setTeamGuest(this.teamRepository.findById(teamIdByRef.get(proposedGame.getTeamGuest().getRef()))
                    .orElseThrow(() -> new CustomException("Team nicht gefunden", Status.NOT_FOUND)));
            this.gameRepository.save(game);
        }

        entity.setStatus(ProposalStatus.COMMITTED);
        entity.setUpdatedAt(Instant.now());
        this.proposalRepository.save(entity);

        return payload.getCategories().stream()
                .map(category -> this.categoryRepository.findById(categoryIdByRef.get(category.getRef()))
                        .orElseThrow(() -> new CustomException("Kategorie nicht gefunden", Status.NOT_FOUND)))
                .collect(Collectors.toList());
    }

    private void resolveTeam(TeamRefDto ref, Map<String, Long> teamIdByRef, Map<String, Long> categoryIdByRef) {
        if (teamIdByRef.containsKey(ref.getRef())) {
            return;
        }
        if (ref.getExistingTeamId() != null) {
            teamIdByRef.put(ref.getRef(), ref.getExistingTeamId());
            return;
        }
        Long categoryId = categoryIdByRef.get(ref.getCategoryRef());
        if (categoryId == null) {
            throw new CustomException("Kategorie-Referenz '" + ref.getCategoryRef() + "' für Platzhalter-Team konnte nicht aufgelöst werden", Status.NOT_FOUND);
        }
        Team team = new Team();
        team.setName(ref.getName());
        team.setPlaceholder(true);
        team.setRank(0);
        team.setCategory(this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException("Kategorie nicht gefunden", Status.NOT_FOUND)));
        Team saved = this.teamRepository.save(team);
        teamIdByRef.put(ref.getRef(), saved.getId());
    }

    // ── Proposal building ───────────────────────────────────────────────────

    /**
     * One category's (or Yetis Cup follow-up's) ordered list of rounds still to be
     * scheduled. A whole round (all its games) is placed as one block into a single
     * hall before moving to the next unit; the hall a unit lands in rotates every
     * round so a category isn't stuck in the same hall.
     */
    private static final class ScheduleUnit {
        private final String categoryRef;
        private final List<List<GameSpec>> rounds;

        private ScheduleUnit(String categoryRef, List<List<GameSpec>> rounds) {
            this.categoryRef = categoryRef;
            this.rounds = rounds;
        }
    }

    private static final class GameSpec {
        private final GameType type;
        private final TeamRefDto home;
        private final TeamRefDto guest;
        private final boolean placeholder;

        private GameSpec(GameType type, TeamRefDto home, TeamRefDto guest, boolean placeholder) {
            this.type = type;
            this.home = home;
            this.guest = guest;
            this.placeholder = placeholder;
        }
    }

    /** A category (or Yetis Cup follow-up) whose placeholder semis/finals still need to be scheduled. */
    private static final class BracketOwner {
        private final String categoryRef;
        private final String categoryName;

        private BracketOwner(String categoryRef, String categoryName) {
            this.categoryRef = categoryRef;
            this.categoryName = categoryName;
        }
    }

    private ProposalPayload buildPayload(List<Category> categories, List<Hall> halls, LocalDateTime startTime) {
        ProposalPayload payload = new ProposalPayload();
        AtomicLong placeholderCounter = new AtomicLong();
        List<ScheduleUnit> units = new ArrayList<>();
        List<BracketOwner> bracketOwners = new ArrayList<>();

        for (Category category : categories) {
            String categoryRef = existingCategoryRef(category);
            payload.getCategories().add(toProposedCategoryDto(category, categoryRef));
            List<Team> teams = this.teamRepository.findByCategoryAndPlaceholderIsFalse(category);

            if (category.getType() == CategoryType.YETIS_CUP) {
                // Rounds 1 & 2: real games among all teams, still in the main category.
                List<List<GameSpec>> mainRounds = roundRobinRoundSpecs(teams, RoundRobinScheduler.partialRoundRobinRounds(teams.size(), 2));
                units.add(new ScheduleUnit(categoryRef, mainRounds));

                String winnerRef = virtualCategoryRef(category.getId(), "winner");
                String loserRef = virtualCategoryRef(category.getId(), "loser");
                String winnerName = category.getName() + " Winner";
                String loserName = category.getName() + " Loser";

                ProposedCategoryDto winnerCategory = new ProposedCategoryDto();
                winnerCategory.setRef(winnerRef);
                winnerCategory.setName(winnerName);
                winnerCategory.setType(CategoryType.SINGLE_CATEGORY);
                winnerCategory.setParentCategoryId(category.getId());
                payload.getCategories().add(winnerCategory);

                ProposedCategoryDto loserCategory = new ProposedCategoryDto();
                loserCategory.setRef(loserRef);
                loserCategory.setName(loserName);
                loserCategory.setType(CategoryType.SINGLE_CATEGORY);
                loserCategory.setParentCategoryId(category.getId());
                payload.getCategories().add(loserCategory);

                // Each half plays on, but the actual team split isn't known yet, so
                // rounds 3-5 are placeholder group rounds among "seed" placeholder
                // teams. Padded with as many empty rounds as the main category used,
                // so they only start once rounds 1 & 2 above have concluded.
                int winnerTeamCount = teams.size() / 2;
                int loserTeamCount = teams.size() - winnerTeamCount;
                units.add(new ScheduleUnit(winnerRef, pad(mainRounds.size(),
                        buildYetisFollowUpRounds(winnerRef, winnerName, winnerTeamCount, placeholderCounter))));
                units.add(new ScheduleUnit(loserRef, pad(mainRounds.size(),
                        buildYetisFollowUpRounds(loserRef, loserName, loserTeamCount, placeholderCounter))));
                bracketOwners.add(new BracketOwner(winnerRef, winnerName));
                bracketOwners.add(new BracketOwner(loserRef, loserName));
            } else if (category.getType() == CategoryType.SINGLE_CATEGORY) {
                List<List<GameSpec>> rounds = new ArrayList<>();
                if (teams.size() >= 2) {
                    // Always exactly 5 rounds (or fewer if the full round robin is
                    // shorter than that) — not everybody necessarily plays everyone.
                    int maxFullRounds = teams.size() % 2 == 0 ? teams.size() - 1 : teams.size();
                    int roundCount = Math.min(5, maxFullRounds);
                    rounds.addAll(roundRobinRoundSpecs(teams, RoundRobinScheduler.partialRoundRobinRounds(teams.size(), roundCount)));
                }
                units.add(new ScheduleUnit(categoryRef, rounds));
                bracketOwners.add(new BracketOwner(categoryRef, category.getName()));
            }
            // DOUBLE_CATEGORIES is intentionally not supported yet.
        }

        Map<Long, LocalDateTime> hallCursor = new HashMap<>();
        for (Hall hall : halls) {
            hallCursor.put(hall.getId(), startTime);
        }
        scheduleUnits(units, halls, hallCursor, payload.getGames());
        scheduleBrackets(bracketOwners, halls, hallCursor, payload.getGames(), placeholderCounter);
        return payload;
    }

    /**
     * 3 placeholder group rounds (round 3, 4, 5) among "seed" placeholder teams
     * standing in for the not-yet-known teams of this half.
     */
    private List<List<GameSpec>> buildYetisFollowUpRounds(String categoryRef, String categoryName, int teamCount, AtomicLong counter) {
        List<TeamRefDto> seeds = new ArrayList<>();
        for (int i = 0; i < teamCount; i++) {
            seeds.add(placeholderTeamRef(counter, categoryRef, categoryName + " Setzung " + (i + 1)));
        }
        List<List<GameSpec>> rounds = new ArrayList<>();
        if (teamCount >= 2) {
            for (List<int[]> round : RoundRobinScheduler.partialRoundRobinRounds(teamCount, 3)) {
                List<GameSpec> specs = new ArrayList<>();
                for (int[] pair : round) {
                    specs.add(new GameSpec(GameType.GROUP_STAGE, seeds.get(pair[0]), seeds.get(pair[1]), true));
                }
                rounds.add(specs);
            }
        }
        return rounds;
    }

    /** Prepends {@code count} empty rounds so this unit only starts once other rounds have concluded. */
    private List<List<GameSpec>> pad(int count, List<List<GameSpec>> rounds) {
        List<List<GameSpec>> padded = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            padded.add(List.of());
        }
        padded.addAll(rounds);
        return padded;
    }

    /**
     * Interleaves all units round by round: in round r, unit i (only if it still has
     * a round left) has its whole round placed as one block into
     * halls.get((i + r) % halls.size()), at that hall's next free 10-minute slots.
     * This alternates the hall a given category plays in from one round to the
     * next, while keeping games of the same category strictly time-ordered (a
     * category's rounds only ever move forward, so its own semis/finals always
     * land after its own group rounds).
     */
    private void scheduleUnits(List<ScheduleUnit> units, List<Hall> halls, Map<Long, LocalDateTime> hallCursor, List<ProposedGameDto> games) {
        int maxRounds = units.stream().mapToInt(u -> u.rounds.size()).max().orElse(0);

        for (int round = 0; round < maxRounds; round++) {
            for (int i = 0; i < units.size(); i++) {
                ScheduleUnit unit = units.get(i);
                if (round >= unit.rounds.size()) {
                    continue;
                }
                List<GameSpec> roundGames = unit.rounds.get(round);
                if (roundGames.isEmpty()) {
                    continue;
                }
                Hall hall = halls.get((i + round) % halls.size());
                LocalDateTime cursor = hallCursor.get(hall.getId());
                for (GameSpec spec : roundGames) {
                    games.add(buildGame(unit.categoryRef, hall, cursor, spec.type, spec.home, spec.guest, spec.placeholder));
                    cursor = cursor.plusMinutes(10);
                }
                hallCursor.put(hall.getId(), cursor);
            }
        }
    }

    private List<List<GameSpec>> roundRobinRoundSpecs(List<Team> teams, List<List<int[]>> rounds) {
        List<List<GameSpec>> result = new ArrayList<>();
        for (List<int[]> round : rounds) {
            List<GameSpec> specs = new ArrayList<>();
            for (int[] pair : round) {
                specs.add(new GameSpec(GameType.GROUP_STAGE, existingTeamRef(teams.get(pair[0])), existingTeamRef(teams.get(pair[1])), false));
            }
            result.add(specs);
        }
        return result;
    }

    /**
     * Schedules every category's placeholder semis + finals, one category at a time,
     * always playing both semis in parallel across the (first) two halls, then both
     * finals in parallel across the same two halls — only once every hall has
     * finished all group-stage rounds for every category.
     */
    private void scheduleBrackets(List<BracketOwner> owners, List<Hall> halls, Map<Long, LocalDateTime> hallCursor,
                                   List<ProposedGameDto> games, AtomicLong counter) {
        if (owners.isEmpty() || halls.isEmpty()) {
            return;
        }
        Hall hallA = halls.get(0);
        Hall hallB = halls.size() > 1 ? halls.get(1) : halls.get(0);
        LocalDateTime cursor = hallCursor.values().stream().max(LocalDateTime::compareTo).orElse(LocalDateTime.now());

        for (BracketOwner owner : owners) {
            List<GameSpec> specs = bracketPlaceholderSpecs(owner.categoryRef, owner.categoryName, counter);
            GameSpec semi1 = specs.get(0);
            GameSpec semi2 = specs.get(1);
            GameSpec bigFinal = specs.get(2);
            GameSpec smallFinal = specs.get(3);

            games.add(buildGame(owner.categoryRef, hallA, cursor, semi1.type, semi1.home, semi1.guest, true));
            games.add(buildGame(owner.categoryRef, hallB, cursor, semi2.type, semi2.home, semi2.guest, true));
            cursor = cursor.plusMinutes(10);

            games.add(buildGame(owner.categoryRef, hallA, cursor, bigFinal.type, bigFinal.home, bigFinal.guest, true));
            games.add(buildGame(owner.categoryRef, hallB, cursor, smallFinal.type, smallFinal.home, smallFinal.guest, true));
            cursor = cursor.plusMinutes(10);
        }

        for (Hall hall : halls) {
            hallCursor.put(hall.getId(), cursor);
        }
    }

    /**
     * 2 placeholder semi-finals followed by 2 placeholder finals (big final + little final).
     */
    private List<GameSpec> bracketPlaceholderSpecs(String categoryRef, String categoryName, AtomicLong counter) {
        TeamRefDto rank1 = placeholderTeamRef(counter, categoryRef, categoryName + " (1.)");
        TeamRefDto rank4 = placeholderTeamRef(counter, categoryRef, categoryName + " (4.)");
        TeamRefDto rank2 = placeholderTeamRef(counter, categoryRef, categoryName + " (2.)");
        TeamRefDto rank3 = placeholderTeamRef(counter, categoryRef, categoryName + " (3.)");
        TeamRefDto winnerHf1 = placeholderTeamRef(counter, categoryRef, "Sieger HF1 " + categoryName);
        TeamRefDto winnerHf2 = placeholderTeamRef(counter, categoryRef, "Sieger HF2 " + categoryName);
        TeamRefDto loserHf1 = placeholderTeamRef(counter, categoryRef, "Verlierer HF1 " + categoryName);
        TeamRefDto loserHf2 = placeholderTeamRef(counter, categoryRef, "Verlierer HF2 " + categoryName);
        return List.of(
                new GameSpec(GameType.SEMI_FINAL, rank1, rank4, true),
                new GameSpec(GameType.SEMI_FINAL, rank2, rank3, true),
                new GameSpec(GameType.FINAL, winnerHf1, winnerHf2, true),
                new GameSpec(GameType.FINAL, loserHf1, loserHf2, true)
        );
    }

    private ProposedGameDto buildGame(String categoryRef, Hall hall, LocalDateTime startDate, GameType type,
                                       TeamRefDto home, TeamRefDto guest, boolean placeholder) {
        ProposedGameDto game = new ProposedGameDto();
        game.setTempId(UUID.randomUUID().toString());
        game.setCategoryRef(categoryRef);
        game.setHallId(hall.getId());
        game.setStartDate(startDate);
        game.setType(type);
        game.setTeamHome(home);
        game.setTeamGuest(guest);
        game.setPlaceholder(placeholder);
        return game;
    }

    private TeamRefDto existingTeamRef(Team team) {
        TeamRefDto ref = new TeamRefDto();
        ref.setRef("team-" + team.getId());
        ref.setName(team.getName());
        ref.setExistingTeamId(team.getId());
        return ref;
    }

    private TeamRefDto placeholderTeamRef(AtomicLong counter, String categoryRef, String name) {
        TeamRefDto ref = new TeamRefDto();
        ref.setRef("ph-" + counter.incrementAndGet());
        ref.setName(name);
        ref.setCategoryRef(categoryRef);
        return ref;
    }

    private ProposedCategoryDto toProposedCategoryDto(Category category, String ref) {
        ProposedCategoryDto dto = new ProposedCategoryDto();
        dto.setRef(ref);
        dto.setName(category.getName());
        dto.setType(category.getType());
        dto.setExistingCategoryId(category.getId());
        if (category.getParentCategory() != null) {
            dto.setParentCategoryId(category.getParentCategory().getId());
        }
        return dto;
    }

    private String existingCategoryRef(Category category) {
        return "existing-" + category.getId();
    }

    private String virtualCategoryRef(long parentCategoryId, String suffix) {
        return "virtual-" + parentCategoryId + "-" + suffix;
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private Tournament findTournament(long tournamentId) {
        return this.tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new CustomException("Turnier mit id " + tournamentId + " nicht gefunden", Status.NOT_FOUND));
    }

    private GameGenerationProposal findProposal(long id) {
        return this.proposalRepository.findById(id)
                .orElseThrow(() -> new CustomException("Vorschlag mit id " + id + " nicht gefunden", Status.NOT_FOUND));
    }

    private ProposalPayload readPayload(GameGenerationProposal entity) {
        try {
            return this.objectMapper.readValue(entity.getPayload(), ProposalPayload.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new CustomException("Vorschlag konnte nicht gelesen werden", Status.NOT_FOUND);
        }
    }

    private String writePayload(ProposalPayload payload) {
        try {
            return this.objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new CustomException("Vorschlag konnte nicht gespeichert werden", Status.NOT_FOUND);
        }
    }

    private GameGenerationProposalDto toDto(GameGenerationProposal entity, ProposalPayload payload) {
        GameGenerationProposalDto dto = new GameGenerationProposalDto();
        dto.setId(entity.getId());
        dto.setTournamentId(entity.getTournament().getId());
        dto.setName(entity.getName());
        dto.setStartTime(entity.getStartTime());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setCategories(payload.getCategories());
        dto.setGames(payload.getGames());
        return dto;
    }

    private ProposalSummaryDto toSummaryDto(GameGenerationProposal entity) {
        ProposalPayload payload = readPayload(entity);
        ProposalSummaryDto dto = new ProposalSummaryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStartTime(entity.getStartTime());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setGameCount(payload.getGames().size());
        return dto;
    }
}
