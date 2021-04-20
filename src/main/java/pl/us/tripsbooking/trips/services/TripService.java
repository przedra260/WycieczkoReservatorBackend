package pl.us.tripsbooking.trips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripUser;
import pl.us.tripsbooking.trips.mappers.TripMapper;
import pl.us.tripsbooking.trips.repositories.TripRepository;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TripMapper tripMapper;

    public List<Trip> getUserTrips(String username) {
        List<TripUser> tripUserList = usersRepository.getUserInfo(username).getTripUserList();
        return tripUserList.stream().map(TripUser::getTrip).collect(Collectors.toList());
    }

    public List<TripListModel> getAllTrips() {
        List<Trip> tripList = new ArrayList<>();
        tripRepository.findAll().forEach(tripList::add);
        return tripMapper.mapToTripListModel(tripList);
    }
}