package pl.us.tripsbooking.trips.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.us.tripsbooking.trips.dto.PaidTripModel;
import pl.us.tripsbooking.trips.dto.TripBookingModel;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripUserReservation;
import pl.us.tripsbooking.trips.repositories.TripRepository;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripUserMapper {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UsersRepository usersRepository;

    public TripUserReservation mapToTripUser(String userEmail, TripBookingModel tripBookingModel) {
        User user = usersRepository.findByEmail(userEmail).get();
        Trip trip = tripRepository.findById(tripBookingModel.getTripId()).orElseThrow();
        BigDecimal price = tripBookingModel.getPricePerSingleParticipant().multiply(BigDecimal.valueOf(tripBookingModel.getParticipants()));
        return new TripUserReservation(tripBookingModel.isMeal(), price, tripBookingModel.getParticipants(), trip, user);
    }

    public List<PaidTripModel> mapToPaidTripModelList(List<TripUserReservation> trips) {
        if (CollectionUtils.isEmpty(trips))
            return null;

        return trips.stream().map(x -> PaidTripModel.builder()
                                                    .price(x.getPrice())
                                                    .tripName(x.getTrip().getTitle())
                                                    .build())
                             .collect(Collectors.toList());
    }
}
