package pl.us.tripsbooking.trips.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookedTripListModel {

    private Integer reservationId;
    private String title;
    private String mainImageUrl;
    private BigDecimal minPrice;
    private Integer guideId;

    public BookedTripListModel(Integer id, String title, String mainImageUrl, BigDecimal minPrice, Integer guideId) {
        this.reservationId = id;
        this.title = title;
        this.mainImageUrl = mainImageUrl;
        this.minPrice = minPrice;
        this.guideId = guideId;
    }
}
