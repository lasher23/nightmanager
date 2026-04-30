package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.*;
import ch.uhc_yetis.nightmanager.domain.repository.CategoryRepository;
import ch.uhc_yetis.nightmanager.domain.repository.RegistrationGroupRepository;
import ch.uhc_yetis.nightmanager.domain.repository.RegistrationRequestRepository;
import ch.uhc_yetis.nightmanager.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationRequestService {

    private final RegistrationRequestRepository requestRepository;
    private final RegistrationGroupRepository groupRepository;
    private final CategoryRepository categoryRepository;
    private final TeamRepository teamRepository;

    public RegistrationRequestService(RegistrationRequestRepository requestRepository,
                                      RegistrationGroupRepository groupRepository,
                                      CategoryRepository categoryRepository,
                                      TeamRepository teamRepository) {
        this.requestRepository = requestRepository;
        this.groupRepository = groupRepository;
        this.categoryRepository = categoryRepository;
        this.teamRepository = teamRepository;
    }

    public RegistrationRequest create(Long groupId, RegistrationRequest request, String contactEmail) {
        RegistrationGroup group = findGroup(groupId);
        if (group.getTournament().getState() != TournamentState.REGISTRATION_OPEN) {
            throw new CustomException("Anmeldungen sind nur möglich wenn das Turnier im Status 'Anmeldung offen' ist", Status.ALREADY_EXISTS);
        }
        request.setId(null);
        request.setContactEmail(contactEmail);
        request.setRegistrationGroup(group);
        request.setStatus(RegistrationRequestStatus.PENDING);
        return requestRepository.save(request);
    }

    public List<RegistrationRequest> findByGroupId(Long groupId) {
        RegistrationGroup group = findGroup(groupId);
        return requestRepository.findByRegistrationGroup(group);
    }

    public List<RegistrationRequest> findByTournamentId(Long tournamentId) {
        return requestRepository.findByRegistrationGroup_Tournament_Id(tournamentId);
    }

    public RegistrationRequest approve(Long id) {
        return updateStatus(id, RegistrationRequestStatus.APPROVED);
    }

    public RegistrationRequest reject(Long id) {
        return updateStatus(id, RegistrationRequestStatus.REJECTED);
    }

    private RegistrationRequest updateStatus(Long id, RegistrationRequestStatus status) {
        RegistrationRequest request = requestRepository.findById(id)
                .orElseThrow(() -> new CustomException("Anmeldung mit id " + id + " nicht gefunden", Status.NOT_FOUND));
        request.setStatus(status);
        return requestRepository.save(request);
    }

    /**
     * Generates categories and teams from approved registration requests.
     * Each {@link GenerationRequest.CategoryAssignment} becomes one Category
     * and its requestIds become Team entities within that category.
     */
    @Transactional
    public List<Category> generateCategories(Long groupId, GenerationRequest generationRequest) {
        RegistrationGroup group = findGroup(groupId);
        Tournament tournament = group.getTournament();
        List<Category> created = new ArrayList<>();

        for (GenerationRequest.CategoryAssignment assignment : generationRequest.getCategories()) {
            Category category = new Category();
            category.setName(assignment.getName());
            category.setState(CategoryState.GROUP_PHASE);
            category.setType(CategoryType.SINGLE_CATEGORY);
            category.setTournament(tournament);
            category.setShowOnDisplay(true);
            Category savedCategory = categoryRepository.save(category);
            created.add(savedCategory);

            for (Long requestId : assignment.getRequestIds()) {
                RegistrationRequest regRequest = requestRepository.findById(requestId)
                        .orElseThrow(() -> new CustomException("Anmeldung " + requestId + " nicht gefunden", Status.NOT_FOUND));
                Team team = new Team();
                team.setName(regRequest.getTeamName());
                team.setCategory(savedCategory);
                team.setPlaceholder(false);
                team.setRank(0);
                teamRepository.save(team);
            }
        }

        return created;
    }

    /**
     * Proposes a balanced split of approved requests in a group into {@code count} categories.
     * Sorting: oldest member first (max age desc), then by average age desc.
     */
    public List<List<Long>> proposeCategories(Long groupId, int count) {
        RegistrationGroup group = findGroup(groupId);
        List<RegistrationRequest> approved = requestRepository.findByRegistrationGroupAndStatus(
                group, RegistrationRequestStatus.APPROVED);

        if (approved.isEmpty()) {
            return Collections.emptyList();
        }

        // Sort: oldest member desc, then average age desc
        LocalDate today = LocalDate.now();
        approved.sort((a, b) -> {
            int oldestA = maxAge(a.getMemberBirthdays(), today);
            int oldestB = maxAge(b.getMemberBirthdays(), today);
            if (oldestA != oldestB) return oldestB - oldestA;
            double avgA = avgAge(a.getMemberBirthdays(), today);
            double avgB = avgAge(b.getMemberBirthdays(), today);
            return Double.compare(avgB, avgA);
        });

        // Balanced split into `count` buckets
        int total = approved.size();
        int base = total / count;
        int remainder = total % count;

        List<List<Long>> buckets = new ArrayList<>();
        int offset = 0;
        for (int i = 0; i < count; i++) {
            int bucketSize = base + (i < remainder ? 1 : 0);
            List<Long> ids = approved.subList(offset, offset + bucketSize)
                    .stream().map(RegistrationRequest::getId).collect(Collectors.toList());
            buckets.add(ids);
            offset += bucketSize;
        }
        return buckets;
    }

    private int maxAge(List<LocalDate> birthdays, LocalDate today) {
        return birthdays.stream()
                .mapToInt(bd -> Period.between(bd, today).getYears())
                .max()
                .orElse(0);
    }

    private double avgAge(List<LocalDate> birthdays, LocalDate today) {
        return birthdays.stream()
                .mapToInt(bd -> Period.between(bd, today).getYears())
                .average()
                .orElse(0.0);
    }

    private RegistrationGroup findGroup(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomException("Anmeldegruppe mit id " + groupId + " nicht gefunden", Status.NOT_FOUND));
    }
}
