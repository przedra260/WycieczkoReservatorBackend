package pl.us.tripsbooking.trips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.us.tripsbooking.trips.entities.TripUser;
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


    public void bookTrip(String userEmail, Integer tripId) {
        tripUserRepository.save(tripUserMapper.mapToTripUser(userEmail, tripId));
    }

    @Transactional
    public void cancelTripBooking(Integer tripId, Integer userId) {
        tripUserRepository.deleteByTripAndUser(tripId, userId);
    }


    public List<UserListModel> getTripParticipant(Integer tripId) {
        List<TripUser> tripUserList = tripUserRepository.findByTripId(tripId);
        List<User> users = tripUserList.stream().map(TripUser::getUser).collect(Collectors.toList());
        return userMapper.mapToUserListModel(users);
    }
}
