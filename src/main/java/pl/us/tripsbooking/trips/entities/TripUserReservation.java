package pl.us.tripsbooking.trips.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.users.entities.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Entity(name = "trips_users_reservation")
public class TripUserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_canceled")
    private Boolean isCanceled;

    @Column(name = "is_historic")
    private Boolean isHistoric;

    @Column(name = "is_meal")
    private Boolean isMeal;

    private BigDecimal price;

    private Integer participants;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public TripUserReservation() {
    }

    public TripUserReservation(boolean isMeal, BigDecimal price, Integer participants, Trip trip, User user) {
        this.isMeal = isMeal;
        this.price = price;
        this.participants = participants;
        this.trip = trip;
        this.user = user;
    }

    @Override
    public String toString() {
        return "TripUserReservation{" +
                "id=" + id +
                ", isCanceled=" + isCanceled +
                ", isHistoric=" + isHistoric +
                ", isMeal=" + isMeal +
                ", price=" + price +
                ", participants=" + participants +
                '}';
    }
}
