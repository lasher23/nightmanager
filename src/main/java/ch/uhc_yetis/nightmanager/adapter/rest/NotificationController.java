package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.NotificationService;
import ch.uhc_yetis.nightmanager.application.TeamService;
import ch.uhc_yetis.nightmanager.domain.model.Category;
import ch.uhc_yetis.nightmanager.domain.model.NotificationLog;
import ch.uhc_yetis.nightmanager.infrastructure.RoleConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final TeamService teamService;
    private final NotificationService notificationService;

    public NotificationController(TeamService teamService, NotificationService notificationService) {
        this.teamService = teamService;
        this.notificationService = notificationService;
    }

    @PostMapping("/category")
    @PreAuthorize("hasAuthority('" + RoleConstants.ADMIN + "')")
    public ResponseEntity<Void> notifyNewCategory(@RequestBody Category category) {
        teamService.notifyCategory(category);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('" + RoleConstants.ADMIN + "')")
    public ResponseEntity<Void> resendNotification(@RequestBody NotificationLog notificationLog) {
        this.notificationService.retryNotification(notificationLog);
        return ResponseEntity.noContent().build();
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('" + RoleConstants.ADMIN + "')")
    public ResponseEntity<List<NotificationLog>> getNotifications() {
        return ResponseEntity.ok(this.notificationService.getAllNotifications());
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<NotificationLog>> getNotificationsByTag(@PathVariable String tag) {
        return ResponseEntity.ok(this.notificationService.getNotificationsForTag(tag));
    }

        // fetch notifications for any of the provided tags (body: ["team-1","team-2"])
        @PostMapping("/tags")
        public ResponseEntity<List<NotificationLog>> getNotificationsByTags(@RequestBody List<String> tags) {
            return ResponseEntity.ok(this.notificationService.getNotificationsForTags(tags));
        }
}
