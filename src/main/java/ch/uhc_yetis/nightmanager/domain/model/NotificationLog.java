package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "notification_log")
public class NotificationLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String text;
    private OffsetDateTime sentTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public OffsetDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(OffsetDateTime sentTime) {
        this.sentTime = sentTime;
    }
}
