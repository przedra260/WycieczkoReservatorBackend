package pl.us.tripsbooking.trips.dto;

import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.trips.enums.TransportForm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookedTripApiModel {

    private Integer tripId;
    private String title;
    private String description;
    private Integer participants;
    private BigDecimal pricePerSingleParticipant;
    private boolean meal;
    private BigDecimal mealPricePerPerson;
    private String departureLocation;
    private String tripLocation;
    private Date startDate;
    private Date endDate;
    private TransportForm formOfTransport;
    private BigDecimal price;
    private String mainImageUrl;
    private List<String> otherImagesUrl;
    private Integer guideId;

    public BookedTripApiModel(Integer tripId, String title, String description, Integer participants, BigDecimal pricePerSingleParticipant, boolean meal,
                              BigDecimal mealPricePerPerson, String departureLocation, String tripLocation, Date startDate, Date endDate, TransportForm formOfTransport,
                              BigDecimal price, String mainImageUrl, List<String> otherImagesUrl, Integer guideId) {
        this.tripId = tripId;
        this.title = title;
        this.description = description;
        this.participants = participants;
        this.pricePerSingleParticipant = pricePerSingleParticipant;
        this.meal = meal;
        this.mealPricePerPerson = mealPricePerPerson;
        this.departureLocation = departureLocation;
        this.tripLocation = tripLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.formOfTransport = formOfTransport;
        this.price = price;
        this.mainImageUrl = mainImageUrl;
        this.otherImagesUrl = otherImagesUrl;
        this.guideId = guideId;
    }
}
