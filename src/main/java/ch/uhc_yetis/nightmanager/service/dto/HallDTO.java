package ch.uhc_yetis.nightmanager.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ch.uhc_yetis.nightmanager.domain.Hall} entity.
 */
public class HallDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HallDTO hallDTO = (HallDTO) o;
        if (hallDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hallDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HallDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
