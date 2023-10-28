package ch.uhc_yetis.nightmanager.application;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final String ACCOUNT_SID = System.getProperty("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getProperty("AUTH_TOKEN");

    @PostConstruct
    void init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendNotification(String notification) {
        Message.creator(new PhoneNumber("+41796841026"), new PhoneNumber("+41799271947"), notification)
                .create();
    }
}
