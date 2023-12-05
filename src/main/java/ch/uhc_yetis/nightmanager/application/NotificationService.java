package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.NotificationLog;
import ch.uhc_yetis.nightmanager.domain.repository.NotificationRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");

    private final NotificationRepository notificationRepository;


    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @PostConstruct
    void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendNotification(String notification, String to) {
        Message.creator(new PhoneNumber(to), new PhoneNumber("+41798076676"), notification)
                .create();
        LOGGER.info("Successfully sent notification '{}' to '{}'", notification, to);
    }

    public void retryNotification(NotificationLog notificationLog) {
        this.notificationRepository.findById(notificationLog.getId())
                .filter(Predicate.not(NotificationLog::isSuccess))
                .ifPresentOrElse(persistedLog -> {
                    sendNotification(persistedLog.getText(), persistedLog.getToNumber());
                    persistedLog.setSuccess(true);
                    notificationRepository.save(persistedLog);
                }, () -> {
                    throw new IllegalArgumentException("No Notification found");
                });
    }

    public List<NotificationLog> getAllNotifications() {
        return this.notificationRepository.findAll();
    }
}
