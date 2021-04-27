package pl.us.tripsbooking.trips.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "trips_images")
public class TripImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public TripImages() {
    }

    public TripImages(String imgUrl, Trip trip) {
        this.imgUrl = imgUrl;
        this.trip = trip;
    }
}
