package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.application.mail.UserMailSender;
import ch.uhc_yetis.nightmanager.domain.model.*;
import ch.uhc_yetis.nightmanager.domain.repository.ApplicationUserRepository;
import ch.uhc_yetis.nightmanager.domain.repository.CategoryRepository;
import ch.uhc_yetis.nightmanager.domain.repository.RegistrationGroupRepository;
import ch.uhc_yetis.nightmanager.domain.repository.RegistrationRequestRepository;
import ch.uhc_yetis.nightmanager.domain.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationRequestService {

    private static final Logger log = LoggerFactory.getLogger(RegistrationRequestService.class);

    private final RegistrationRequestRepository requestRepository;
    private final RegistrationGroupRepository groupRepository;
    private final CategoryRepository categoryRepository;
    private final TeamRepository teamRepository;
    private final ApplicationUserRepository applicationUserRepository;
    private final UserMailSender graphUserMailService;

    public RegistrationRequestService(RegistrationRequestRepository requestRepository,
                                      RegistrationGroupRepository groupRepository,
                                      CategoryRepository categoryRepository,
                                      TeamRepository teamRepository,
                                      ApplicationUserRepository applicationUserRepository,
                                      UserMailSender graphUserMailService) {
        this.requestRepository = requestRepository;
        this.groupRepository = groupRepository;
        this.categoryRepository = categoryRepository;
        this.teamRepository = teamRepository;
        this.applicationUserRepository = applicationUserRepository;
        this.graphUserMailService = graphUserMailService;
    }

    public RegistrationRequest create(Long groupId, RegistrationRequest request, String contactEmail) {
        RegistrationGroup group = findGroup(groupId);
        if (group.getTournament().getState() != TournamentState.REGISTRATION_OPEN) {
            throw new CustomException("Anmeldungen sind nur möglich wenn das Turnier im Status 'Anmeldung offen' ist", Status.ALREADY_EXISTS);
        }
        if (request.getTeamLeader() == null || request.getTeamLeader().isBlank()) {
            throw new CustomException("Teamchef ist erforderlich", Status.ALREADY_EXISTS);
        }
        request.setTeamLeader(request.getTeamLeader().trim());
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

    public List<RegistrationRequest> findMine(Long tournamentId, String contactEmail) {
        return requestRepository.findByContactEmailIgnoreCaseAndRegistrationGroup_Tournament_IdOrderByCreatedAtDesc(contactEmail, tournamentId);
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
     * Sends the "registration confirmed" email to the registration's contact address, via
     * Microsoft Graph, as the acting admin (so it's sent from their own mailbox). Does not
     * change any persisted state, so it is safe to call again if it fails (e.g. retry button).
     */
    public void sendApprovalEmail(Long requestId, String actingUserEmail) {
        RegistrationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new CustomException("Anmeldung mit id " + requestId + " nicht gefunden", Status.NOT_FOUND));

        ApplicationUser actingUser = applicationUserRepository.findByEmail(actingUserEmail);
        if (actingUser == null || actingUser.getMicrosoftPrincipalName() == null) {
            throw new CustomException(
                    "Kein Microsoft-Konto verknüpft — bitte einmal über Microsoft anmelden, um E-Mails versenden zu können.",
                    Status.MAIL_FAILED);
        }

        RegistrationGroup group = request.getRegistrationGroup();
        Tournament tournament = group.getTournament();

        try {
            graphUserMailService.sendMailAsUser(
                    actingUser.getMicrosoftPrincipalName(),
                    "Anmeldung bestätigt – " + request.getTeamName(),
                    buildApprovalEmailHtml(request, group, tournament),
                    request.getContactEmail());
        } catch (Exception e) {
            log.error("Failed to send approval email for registration request {}", requestId, e);
            throw new CustomException("E-Mail konnte nicht gesendet werden: " + e.getMessage(), Status.MAIL_FAILED);
        }
    }

    private String buildApprovalEmailHtml(RegistrationRequest request, RegistrationGroup group, Tournament tournament) {
        String teamName = request.getTeamName();
        String groupName = group.getName();
        String tournamentName = tournament.getName();

        return """
                <!DOCTYPE html>
                <html lang="de">
                <body style="margin:0;padding:0;background:#f2f2f7;font-family:'Segoe UI',Arial,sans-serif;">
                  <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="background:#f2f2f7;padding:32px 0;">
                    <tr>
                      <td align="center">
                        <table role="presentation" width="480" cellpadding="0" cellspacing="0"
                               style="background:#ffffff;border-radius:16px;overflow:hidden;box-shadow:0 8px 24px rgba(0,0,0,0.08);">
                          <tr>
                            <td style="background:linear-gradient(135deg,#059669,#10b981);padding:28px 32px;">
                              <span style="color:#ffffff;font-size:20px;font-weight:700;letter-spacing:0.5px;">🏒 Nightmanager</span>
                            </td>
                          </tr>
                          <tr>
                            <td style="padding:32px;">
                              <div style="text-align:center;margin-bottom:20px;">
                                <span style="display:inline-block;width:56px;height:56px;line-height:56px;border-radius:50%%;
                                             background:#ecfdf5;color:#059669;font-size:28px;">&#10003;</span>
                              </div>
                              <h1 style="margin:0 0 12px;font-size:22px;color:#1a1a2e;text-align:center;">Anmeldung bestätigt</h1>
                              <p style="margin:0 0 24px;font-size:15px;line-height:1.6;color:#555;text-align:center;">
                                Die Anmeldung von <strong>%s</strong> für das Turnier <strong>%s</strong> wurde soeben bestätigt.
                              </p>
                              <table role="presentation" width="100%%" cellpadding="0" cellspacing="0"
                                     style="background:#f9fafb;border-radius:12px;border:1px solid #eee;">
                                <tr>
                                  <td style="padding:16px 20px;font-size:14px;color:#444;">
                                    <div style="margin-bottom:8px;"><strong>Team:</strong> %s</div>
                                    <div style="margin-bottom:8px;"><strong>Anmeldegruppe:</strong> %s</div>
                                    <div><strong>Turnier:</strong> %s</div>
                                  </td>
                                </tr>
                              </table>
                              <p style="margin:24px 0 0;font-size:13px;line-height:1.6;color:#888;">
                                Diese Einladung wurde für die Anmeldegruppe „%s" verschickt. Bei Fragen antworte
                                einfach auf diese E-Mail.
                              </p>
                            </td>
                          </tr>
                          <tr>
                            <td style="background:#fafafa;padding:16px 32px;border-top:1px solid #eee;">
                              <p style="margin:0;font-size:12px;color:#aaa;">Diese E-Mail wurde automatisch generiert.</p>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </body>
                </html>
                """.formatted(teamName, tournamentName, teamName, groupName, tournamentName, groupName);
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
            category.setType(assignment.getType() != null ? assignment.getType() : CategoryType.SINGLE_CATEGORY);
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
