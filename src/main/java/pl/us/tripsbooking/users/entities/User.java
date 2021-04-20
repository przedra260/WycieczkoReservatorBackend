package pl.us.tripsbooking.users.entities;


import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripUser;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String login;

    private String email;

    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "guideId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> guideTripsList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripUser> tripUserList;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isBlocked=" + isBlocked +
                ", role=" + role +
                '}';
    }
}