package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.GenerationRequest;
import ch.uhc_yetis.nightmanager.application.RegistrationGroupService;
import ch.uhc_yetis.nightmanager.application.RegistrationRequestService;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.model.RegistrationGroup;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/registration-groups")
public class RegistrationGroupController {

    private final RegistrationGroupService registrationGroupService;
    private final RegistrationRequestService registrationRequestService;

    public RegistrationGroupController(RegistrationGroupService registrationGroupService,
                                       RegistrationRequestService registrationRequestService) {
        this.registrationGroupService = registrationGroupService;
        this.registrationRequestService = registrationRequestService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_GROUP_LIST + "')")
    public List<RegistrationGroup> getByTournament(@RequestParam Long tournamentId) {
        return registrationGroupService.findByTournamentId(tournamentId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_GROUP_LIST + "')")
    public RegistrationGroup getById(@PathVariable Long id) {
        return registrationGroupService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_GROUP_CREATE + "')")
    public RegistrationGroup create(@RequestParam Long tournamentId, @RequestBody RegistrationGroup group) {
        return registrationGroupService.create(tournamentId, group);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_GROUP_UPDATE + "')")
    public RegistrationGroup update(@PathVariable Long id, @RequestBody RegistrationGroup group) {
        RegistrationGroup existing = registrationGroupService.findById(id);
        existing.setName(group.getName());
        existing.setRequiresAge(group.isRequiresAge());
        return registrationGroupService.save(existing);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_GROUP_DELETE + "')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        registrationGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Returns a balanced proposal: N buckets of request IDs sorted by age.
     */
    @GetMapping("/{id}/propose")
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_REQUEST_APPROVE + "')")
    public List<List<Long>> propose(@PathVariable Long id, @RequestParam int count) {
        return registrationRequestService.proposeCategories(id, count);
    }

    /**
     * Generates actual Category and Team entities from the confirmed assignment.
     */
    @PostMapping("/{id}/generate")
    @PreAuthorize("hasAuthority('" + RoleConstants.REGISTRATION_REQUEST_APPROVE + "')")
    public List<Category> generate(@PathVariable Long id, @RequestBody GenerationRequest request) {
        return registrationRequestService.generateCategories(id, request);
    }
}
