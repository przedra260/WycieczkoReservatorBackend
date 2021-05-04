package pl.us.tripsbooking.trips.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TripBookingModel {

    private Integer tripId;
    private Integer participants;
    private boolean isMeal;
    private BigDecimal pricePerSingleParticipant;
}
