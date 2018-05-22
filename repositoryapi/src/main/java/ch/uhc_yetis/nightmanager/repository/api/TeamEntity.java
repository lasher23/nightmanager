package ch.uhc_yetis.nightmanager.repository.api;
import javax.persistence.*;

@Entity
@Table(name = "teams",catalog = "nightmanager")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }
}
