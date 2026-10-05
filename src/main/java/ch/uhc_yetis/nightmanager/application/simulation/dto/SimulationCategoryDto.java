package ch.uhc_yetis.nightmanager.application.simulation.dto;

import ch.uhc_yetis.nightmanager.domain.model.CategoryType;

public class SimulationCategoryDto {
    private long id;
    private String name;
    private CategoryType type;
    private int teamCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }
}
