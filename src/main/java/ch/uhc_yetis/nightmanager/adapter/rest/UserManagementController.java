package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.UpdateUserRequest;
import ch.uhc_yetis.nightmanager.application.UserDto;
import ch.uhc_yetis.nightmanager.application.UserManagementService;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserManagementController {

    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.USER_LIST + "')")
    public List<UserDto> getAll() {
        return userManagementService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.USER_GET + "')")
    public UserDto getById(@PathVariable Long id) {
        return userManagementService.findById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.USER_UPDATE + "')")
    public UserDto update(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        return userManagementService.update(id, request);
    }
}
