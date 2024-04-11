package ch.uhc_yetis.nightmanager.domain.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;
    @Column(unique = true)
    private String name;
    @Column(name = "flagPlaceholder")
    private boolean placeholder;
    @Column
    private int rank;
    @Column(name = "flag_paid")
    private Boolean paid;
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "team_notification_log_assignment",
            joinColumns = @JoinColumn(name = "fk_team"), inverseJoinColumns = @JoinColumn(name = "fk_notification"))
    private List<NotificationLog> notifications;

    public boolean isPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isPlaceholder() {
        return this.placeholder;
    }

    public void setPlaceholder(boolean placeholder) {
        this.placeholder = placeholder;
    }

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
        Team team = (Team) o;
        return this.id == team.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<NotificationLog> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationLog> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", placeholder=" + placeholder +
                ", rank=" + rank +
                ", paid=" + paid +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", notifications=" + notifications +
                '}';
    }
}
