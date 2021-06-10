package pl.us.tripsbooking.trips.services;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.us.tripsbooking.exceptions.ExceptionCodes;
import pl.us.tripsbooking.exceptions.TripsBookingException;
import pl.us.tripsbooking.trips.dto.PaidTripModel;
import pl.us.tripsbooking.trips.dto.TripBookingModel;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripUserReservation;
import pl.us.tripsbooking.trips.mappers.TripMapper;
import pl.us.tripsbooking.trips.mappers.TripUserMapper;
import pl.us.tripsbooking.trips.repositories.TripUserRepository;
import pl.us.tripsbooking.users.dto.UserListModel;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.mappers.UserMapper;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TripUserReservationService {

    @Autowired
    private TripUserMapper tripUserMapper;

    @Autowired
    private TripUserRepository tripUserRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private UsersRepository usersRepository;


    @Transactional
    public void bookTrip(String userEmail, TripBookingModel tripBookingModel) {
        Optional<User> user = usersRepository.findByEmail(userEmail);
        if (user.isEmpty())
            throw new TripsBookingException(ExceptionCodes.USER_DOES_NOT_EXIST);

        if (user.get().getBalance() < (tripBookingModel.getPricePerSingleParticipant().doubleValue() *
                                       tripBookingModel.getParticipants()))
            throw new TripsBookingException(ExceptionCodes.NOT_ENOUGH_MONEY);

        user.get().setBalance(user.get().getBalance() - (int) (tripBookingModel.getPricePerSingleParticipant().doubleValue() *
                                                               tripBookingModel.getParticipants()));
        usersRepository.save(user.get());
        tripUserRepository.save(tripUserMapper.mapToTripUser(userEmail, tripBookingModel));
    }

    @Transactional
    public void cancelTripReservation(Integer reservationId) {
        tripUserRepository.cancelTripReservation(reservationId);
    }

    public List<UserListModel> getTripParticipant(Integer tripId) {
        List<TripUserReservation> tripUserReservationList = tripUserRepository.findByTripId(tripId);
        List<User> userList = tripUserReservationList.stream().map(TripUserReservation::getUser).collect(Collectors.toList());
        return userMapper.mapToUserListModel(userList);
    }

    public List<TripListModel> getBookedTrips(Integer userId) {
        List<TripUserReservation> tripUserReservationList = tripUserRepository.findByUserId(userId);
        List<Trip> tripList = tripUserReservationList.stream().map(TripUserReservation::getTrip).collect(Collectors.toList());
        return tripMapper.mapToTripListModel(tripList);
    }

    public List<PaidTripModel> getUserPaidTrips(Integer userId) {
        List<TripUserReservation> trips = tripUserRepository.findPaidTripsByUserIdOrderedDesc(userId);
        return tripUserMapper.mapToPaidTripModelList(trips);
    }

    public void markTripsAsHistorical() {
        tripUserRepository.markTripsAsHistorical();
    }
}
