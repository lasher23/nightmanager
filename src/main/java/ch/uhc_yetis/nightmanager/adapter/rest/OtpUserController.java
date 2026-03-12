package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.CreateOtpUserRequest;
import ch.uhc_yetis.nightmanager.application.OtpUserDto;
import ch.uhc_yetis.nightmanager.application.OtpUserService;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/otp-users")
public class OtpUserController {

    private final OtpUserService otpUserService;

    public OtpUserController(OtpUserService otpUserService) {
        this.otpUserService = otpUserService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.OTP_USER_LIST + "')")
    public List<OtpUserDto> findAll() {
        return otpUserService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.OTP_USER_GET + "')")
    public OtpUserDto findById(@PathVariable Long id) {
        return otpUserService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('" + RoleConstants.OTP_USER_CREATE + "')")
    public OtpUserDto create(@RequestBody CreateOtpUserRequest request) {
        return otpUserService.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('" + RoleConstants.OTP_USER_DELETE + "')")
    public void delete(@PathVariable Long id) {
        otpUserService.delete(id);
    }
}
