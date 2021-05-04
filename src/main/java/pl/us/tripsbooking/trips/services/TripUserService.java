package pl.us.tripsbooking.trips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripUser;
import pl.us.tripsbooking.trips.mappers.TripMapper;
import pl.us.tripsbooking.trips.mappers.TripUserMapper;
import pl.us.tripsbooking.trips.repositories.TripUserRepository;
import pl.us.tripsbooking.users.dto.UserListModel;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.mappers.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripUserService {

    @Autowired
    private TripUserMapper tripUserMapper;

    @Autowired
    private TripUserRepository tripUserRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TripMapper tripMapper;


    public void bookTrip(String userEmail, Integer tripId) {
        tripUserRepository.save(tripUserMapper.mapToTripUser(userEmail, tripId));
    }

    @Transactional
    public void cancelTripBooking(Integer tripId, Integer userId) {
        tripUserRepository.deleteByTripAndUser(tripId, userId);
    }

    public List<UserListModel> getTripParticipant(Integer tripId) {
        List<TripUser> tripUserList = tripUserRepository.findByTripId(tripId);
        List<User> userList = tripUserList.stream().map(TripUser::getUser).collect(Collectors.toList());
        return userMapper.mapToUserListModel(userList);
    }

    public List<TripListModel> getBookedTrips(Integer userId) {
        List<TripUser> tripUserList = tripUserRepository.findByUserId(userId);
        List<Trip> tripList = tripUserList.stream().map(TripUser::getTrip).collect(Collectors.toList());
        return tripMapper.mapToTripListModel(tripList);
    }
}
