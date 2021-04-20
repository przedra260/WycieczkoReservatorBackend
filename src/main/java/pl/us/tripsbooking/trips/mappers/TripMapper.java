package pl.us.tripsbooking.trips.mappers;

import org.springframework.stereotype.Component;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.entities.Trip;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripMapper {

    public List<TripListModel> mapToTripListModel(List<Trip> tripList) {
        return tripList.stream().map(trip -> new TripListModel(trip.getId(), trip.getTitle(), trip.getMainImgUrl(), trip.getMinPrice())).collect(Collectors.toList());
    }
}
