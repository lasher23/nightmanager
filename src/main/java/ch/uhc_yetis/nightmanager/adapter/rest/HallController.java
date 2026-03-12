package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.HallService;
import ch.uhc_yetis.nightmanager.domain.model.Hall;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {
    private HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('" + RoleConstants.HALL_LIST + "')")
    public List<Hall> getAll() {
        return this.hallService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('" + RoleConstants.HALL_GET + "')")
    public Hall getById(@PathVariable long id) {
        return this.hallService.getById(id);
    }
}
