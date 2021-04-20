package pl.us.tripsbooking.trips.entities;

import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.trips.enums.TransportForm;
import pl.us.tripsbooking.users.entities.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @Column(name = "main_img_url")
    private String mainImgUrl;

    @Column(name = "min_price")
    private BigDecimal minPrice;

    private String description;

    @Column(name = "price_per_person")
    private BigDecimal pricePerPerson;

    @Column(name = "price_per_room")
    private BigDecimal pricePerRoom;

    private boolean meal;

    @Column(name = "price_per_daily_meal")
    private BigDecimal pricePerDailyMeal;

    @Column(name = "departure_location")
    private String departureLocation;

    @Column(name = "trip_location")
    private String tripLocation;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "room_sizes")
    private String roomSizes;

    private String participants;

//    @Column(name = "transport_form")
//    @Enumerated(EnumType.ORDINAL)
//    private TransportForm transportForm;

    @ManyToOne
    @JoinColumn(name = "guide_id", nullable = false)
    private User guideId;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripUser> tripUserList;

    public Trip() {
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                ", minPrice=" + minPrice +
                ", description='" + description + '\'' +
                ", pricePerPerson=" + pricePerPerson +
                ", pricePerRoom=" + pricePerRoom +
                ", meal=" + meal +
                ", pricePerDailyMeal=" + pricePerDailyMeal +
                ", departureLocation='" + departureLocation + '\'' +
                ", tripLocation='" + tripLocation + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", roomSizes='" + roomSizes + '\'' +
                ", participants='" + participants + '\'' +
//                ", transportForm=" + transportForm +
                ", guideId=" + guideId +
                '}';
    }
}