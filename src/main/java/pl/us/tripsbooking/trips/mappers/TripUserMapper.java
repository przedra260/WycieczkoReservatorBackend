package pl.us.tripsbooking.trips.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.us.tripsbooking.trips.dto.TripBookingModel;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripUserReservation;
import pl.us.tripsbooking.trips.repositories.TripRepository;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.math.BigDecimal;

@Component
public class TripUserMapper {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UsersRepository usersRepository;

    public TripUserReservation mapToTripUser(String userEmail, TripBookingModel tripBookingModel) {
        User user = usersRepository.findByEmail(userEmail);
        Trip trip = tripRepository.findById(tripBookingModel.getTripId()).orElseThrow();
        BigDecimal price = tripBookingModel.getPricePerSingleParticipant().multiply(BigDecimal.valueOf(tripBookingModel.getParticipants()));
        return new TripUserReservation(tripBookingModel.isMeal(), price, tripBookingModel.getParticipants(), trip, user);
    }
}
