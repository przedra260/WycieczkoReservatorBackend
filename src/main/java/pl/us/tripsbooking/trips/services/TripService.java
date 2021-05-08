package pl.us.tripsbooking.trips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.us.tripsbooking.exceptions.ExceptionCodes;
import pl.us.tripsbooking.exceptions.TripsBookingException;
import pl.us.tripsbooking.trips.dto.TripApiModel;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripUserReservation;
import pl.us.tripsbooking.trips.mappers.TripMapper;
import pl.us.tripsbooking.trips.repositories.TripRepository;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
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

    public List<TripListModel> getGuideTrips(String email) {
        List<Trip> tripList = usersRepository.findByEmail(email).get().getGuideTripsList();
        return tripMapper.mapToTripListModel(tripList);
    }

    public List<TripListModel> getUserTrips(String email) {
        List<TripUserReservation> tripUserReservationList = usersRepository.findByEmail(email).get().getTripUserReservationList();
        List<Trip> tripList = tripUserReservationList.stream().map(TripUserReservation::getTrip).collect(Collectors.toList());
        return tripMapper.mapToTripListModel(tripList);
    }

    public List<TripListModel> getAllTrips() {
        List<Trip> tripList = new ArrayList<>();
        tripRepository.findAll().forEach(tripList::add);
        return tripMapper.mapToTripListModel(tripList);
    }

    public TripApiModel getTripDetails(Integer tripId) {
        return tripMapper.mapToTripApiModel(tripRepository.findById(tripId).orElseThrow());
    }

    public void saveTrip(TripApiModel tripApiModel) {
        Trip trip = tripMapper.mapToTrip(tripApiModel);
        tripRepository.save(trip);
    }

    public void deleteTrip(Integer tripId) {
        tripRepository.deleteById(tripId);
    }

    @Transactional
    public void assignGuide(Integer tripId, Integer guideId) {
        checkGuideAvailability(tripId, guideId);
        tripRepository.assignGuide(tripId, guideId);
    }

    public void checkGuideAvailability(Integer tripId, Integer guideId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new TripsBookingException(ExceptionCodes.TRIP_DOES_NOT_EXIST));
        List<Trip> guideTrips = tripRepository.findByGuideId(guideId);
        List<SimpleEntry<Date, Date>> startEndDatePairList = guideTrips.stream().map(a -> new SimpleEntry<>(a.getStartDate(), a.getEndDate())).collect(Collectors.toList());
        boolean blockingDates = startEndDatePairList.stream().anyMatch(e -> !e.getKey().after(trip.getEndDate()) && !trip.getStartDate().after(e.getValue()));
        if (blockingDates) {
            throw new TripsBookingException(ExceptionCodes.GUIDE_IS_NOT_AVAILABLE);
        }
    }
}
