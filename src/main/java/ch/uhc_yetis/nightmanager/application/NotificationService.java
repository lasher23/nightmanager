package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.application.onesignal.Notification;
import ch.uhc_yetis.nightmanager.domain.model.NotificationLog;
import ch.uhc_yetis.nightmanager.domain.repository.NotificationRepository;
// removed unused imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.function.Predicate;

@Service
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private static final String ONE_SIGNAL_API_KEY = System.getenv("ONE_SIGNAL_API_KEY");

    private final NotificationRepository notificationRepository;
    private final String appId;


    public NotificationService(NotificationRepository notificationRepository, @Value("${onesignal.app-id}") String appId) {
        this.notificationRepository = notificationRepository;
        this.appId = appId;
    }

    public void sendNotification(String notification, String tagId, String url) {
        RestClient.create().post()
                .uri("https://api.onesignal.com/notifications")
                .header("Authorization", "Basic " + ONE_SIGNAL_API_KEY)
                .body(new Notification(appId, "push", List.of("Subscribed Users"), List.of(new Notification.Filter(
                        "tag", tagId, "true", "="
                )), new Notification.Content(notification, notification), url))
                .retrieve()
                .toBodilessEntity();
        LOGGER.info("Successfully sent notification '{}' tagId '{}'", notification, tagId);
    }

    public void retryNotification(NotificationLog notificationLog) {
        this.notificationRepository.findById(notificationLog.getId())
                .filter(Predicate.not(NotificationLog::isSuccess))
                .ifPresentOrElse(persistedLog -> {
                    sendNotification(persistedLog.getText(), persistedLog.getTagId(), persistedLog.getUrl());
                    persistedLog.setSuccess(true);
                    notificationRepository.save(persistedLog);
                }, () -> {
                    throw new IllegalArgumentException("No Notification found");
                });
    }

    public List<NotificationLog> getAllNotifications() {
        return this.notificationRepository.findAll();
    }

    public List<NotificationLog> getNotificationsForTag(String tag) {
        return this.notificationRepository.findByTagIdOrderBySentTimeDesc(tag);
    }

    public List<NotificationLog> getNotificationsForTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) return List.of();
        return this.notificationRepository.findByTagIdInOrderBySentTimeDesc(tags);
    }
}
