package pl.us.tripsbooking.trips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.us.tripsbooking.trips.mappers.TripUserMapper;
import pl.us.tripsbooking.trips.repositories.TripUserRepository;

@Component
public class TripUserService {

    @Autowired
    private TripUserMapper tripUserMapper;

    @Autowired
    private TripUserRepository tripUserRepository;


    public void bookTrip(String userEmail, Integer tripId) {
        tripUserRepository.save(tripUserMapper.mapToTripUser(userEmail, tripId));
    }

    @Transactional
    public void cancelTripBooking(Integer tripId, Integer userId) {
        tripUserRepository.deleteByTripAndUser(tripId, userId);
    }
}