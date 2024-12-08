package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "notification_log")
public class NotificationLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(length = 10000)
    private String text;
    private String tagId;
    private OffsetDateTime sentTime;
    private boolean success;
    private String reference;
    private String url;

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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String to) {
        this.tagId = to;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
