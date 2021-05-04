package pl.us.tripsbooking.trips.entities;

import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.trips.enums.TransportForm;
import pl.us.tripsbooking.users.entities.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(name = "main_img_url")
    private String mainImgUrl;

    @Column(name = "min_price")
    private BigDecimal minPrice;

    private String description;

    @Column(name = "price_per_person")
    private BigDecimal pricePerPerson;

    private boolean meal;

    @Column(name = "meal_price_per_person")
    private BigDecimal mealPricePerPerson;

    @Column(name = "departure_location")
    private String departureLocation;

    @Column(name = "trip_location")
    private String tripLocation;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private String participants;

    @Enumerated(EnumType.STRING)
    private TransportForm transport;

    @ManyToOne
    @JoinColumn(name = "guide_id")
    private User guideId;

    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<TripUser> tripUserList = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripImages> tripImagesList = new ArrayList<>();

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
                ", meal=" + meal +
                ", mealPricePerPerson=" + mealPricePerPerson +
                ", departureLocation='" + departureLocation + '\'' +
                ", tripLocation='" + tripLocation + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", participants='" + participants + '\'' +
                ", transport=" + transport +
                ", guideId=" + guideId +
                '}';
    }
}
