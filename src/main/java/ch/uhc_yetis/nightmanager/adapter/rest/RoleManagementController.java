package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.RoleDto;
import ch.uhc_yetis.nightmanager.application.RoleManagementService;
import ch.uhc_yetis.nightmanager.application.RoleRequest;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleManagementController {

    private final RoleManagementService roleManagementService;

    public RoleManagementController(RoleManagementService roleManagementService) {
        this.roleManagementService = roleManagementService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.ROLE_LIST + "')")
    public List<RoleDto> getAll() {
        return roleManagementService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.ROLE_GET + "')")
    public RoleDto getById(@PathVariable Long id) {
        return roleManagementService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('" + RoleConstants.ROLE_CREATE + "')")
    public RoleDto create(@RequestBody RoleRequest request) {
        return roleManagementService.create(request);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.ROLE_UPDATE + "')")
    public RoleDto update(@PathVariable Long id, @RequestBody RoleRequest request) {
        return roleManagementService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('" + RoleConstants.ROLE_DELETE + "')")
    public void delete(@PathVariable Long id) {
        roleManagementService.delete(id);
    }
}
