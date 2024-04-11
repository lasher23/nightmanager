package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    public String getName() {
        return name;
    }
}