package ch.uhc_yetis.nightmanager.domain.model;

import javax.persistence.*;

@Entity(name = "halls")
@Table
public class Hall {
    @Id
    @GeneratedValue
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

    public void setName(String name) {
        this.name = name;
    }
}
