package pl.us.tripsbooking.trips.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripUser;
import pl.us.tripsbooking.trips.repositories.TripRepository;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.repositories.UsersRepository;

@Component
public class TripUserMapper {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UsersRepository usersRepository;

    public TripUser mapToTripUser(String userEmail, Integer tripId) {
        User user = usersRepository.findByEmail(userEmail);
        Trip trip = tripRepository.findById(tripId).orElseThrow();
        return new TripUser(user, trip);
    }
}
