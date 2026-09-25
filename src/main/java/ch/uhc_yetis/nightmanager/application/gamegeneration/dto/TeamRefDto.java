package ch.uhc_yetis.nightmanager.application.gamegeneration.dto;

/**
 * Reference to a team within a proposal. Either points to an already
 * persisted team ({@link #existingTeamId} set) or describes a placeholder
 * team that still needs to be created on commit (identified by {@link #ref}).
 */
public class TeamRefDto {
    private String ref;
    private String name;
    private Long existingTeamId;
    private String categoryRef;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExistingTeamId() {
        return existingTeamId;
    }

    public void setExistingTeamId(Long existingTeamId) {
        this.existingTeamId = existingTeamId;
    }

    public String getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(String categoryRef) {
        this.categoryRef = categoryRef;
    }
}
