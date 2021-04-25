package pl.us.tripsbooking.trips.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TripListModel {

    private Integer number;
    private String title;
    private String mainImageUrl;
    private BigDecimal minPrice;

    public TripListModel(Integer number, String title, String mainImageUrl, BigDecimal minPrice) {
        this.number = number;
        this.title = title;
        this.mainImageUrl = mainImageUrl;
        this.minPrice = minPrice;
    }
}
