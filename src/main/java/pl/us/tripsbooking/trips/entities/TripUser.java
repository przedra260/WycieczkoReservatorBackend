package pl.us.tripsbooking.trips.entities;

import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.users.entities.User;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "trips_users")
public class TripUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public TripUser() {
    }

    public TripUser(User user, Trip trip) {
        this.user = user;
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "TripUser{" +
                "id=" + id +
                ", trip=" + trip +
                ", user=" + user +
                '}';
    }
}
