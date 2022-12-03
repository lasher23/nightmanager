package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity(name = "halls")
@Table
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Hall hall = (Hall) o;
        return this.id == hall.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public void setName(String name) {
        this.name = name;
    }
}
