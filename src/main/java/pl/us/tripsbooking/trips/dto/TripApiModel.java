package pl.us.tripsbooking.trips.dto;

import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.trips.enums.TransportForm;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TripApiModel {

    private Integer id;
    private String title;
    private String description;
    private List<Integer> participants;
    private BigDecimal pricePerSingleParticipant;
    private boolean meal;
    private BigDecimal mealPricePerPerson;
    private String departureLocation;
    private String tripLocation;
    private Date startDate;
    private Date endDate;
    private TransportForm formOfTransport;
    private String mainImageUrl;
    private List<String> otherImagesUrl;
    private Integer guideId;

    public TripApiModel(Integer id, String title, String description, List<Integer> participants, BigDecimal pricePerSingleParticipant,
                        boolean meal, BigDecimal mealPricePerPerson, String departureLocation, String tripLocation, Date startDate, Date endDate,
                        TransportForm formOfTransport, String mainImageUrl, List<String> otherImagesUrl, Integer guideId) {
        this.id = id;
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
        this.mainImageUrl = mainImageUrl;
        this.otherImagesUrl = otherImagesUrl;
        this.guideId = guideId;
    }
}
