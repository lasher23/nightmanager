package ch.uhc_yetis.nightmanager.application.gamegeneration.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProposeRequest {
    private String name;
    private List<Long> categoryIds;
    private LocalDateTime startTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
