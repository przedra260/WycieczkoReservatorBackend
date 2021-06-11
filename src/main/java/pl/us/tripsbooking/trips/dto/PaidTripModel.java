package pl.us.tripsbooking.trips.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PaidTripModel {
    private String tripName;
    private BigDecimal price;
}
