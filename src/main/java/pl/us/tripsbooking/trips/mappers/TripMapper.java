package pl.us.tripsbooking.trips.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.us.tripsbooking.exceptions.ExceptionCodes;
import pl.us.tripsbooking.exceptions.TripsBookingException;
import pl.us.tripsbooking.trips.dto.TripApiModel;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripImages;
import pl.us.tripsbooking.trips.repositories.TripRepository;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripMapper {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TripRepository tripRepository;

    public List<TripListModel> mapToTripListModel(List<Trip> tripList) {
        return tripList
                .stream()
                .map(trip -> new TripListModel(
                        trip.getId(),
                        trip.getTitle(),
                        trip.getMainImgUrl(),
                        trip.getMinPrice(),
                        trip.getGuide().getId()))
                .collect(Collectors.toList());
    }

    public TripApiModel mapToTripApiModel(Trip trip) {
        List<Integer> participants = Arrays.stream(trip.getParticipants().split(",")).map(Integer::valueOf).collect(Collectors.toList());
        List<String> urls = trip.getTripImagesList().stream().map(TripImages::getImgUrl).collect(Collectors.toList());

        return new TripApiModel(
                trip.getId(),
                trip.getTitle(),
                trip.getDescription(),
                participants,
                trip.getPricePerPerson(),
                trip.isMeal(),
                trip.getMealPricePerPerson(),
                trip.getDepartureLocation(),
                trip.getTripLocation(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getTransport(),
                trip.getMainImgUrl(),
                urls,
                trip.getGuide() != null ? trip.getGuide().getId() : null
        );
    }

    public Trip mapToTrip(TripApiModel tripApiModel) {
        String participants = tripApiModel.getParticipants().stream().map(Object::toString).collect(Collectors.joining(","));

        Trip trip;
        if (tripApiModel.getId() != null) {
            trip = tripRepository.findById(tripApiModel.getId()).orElseThrow(() -> new TripsBookingException(ExceptionCodes.TRIP_DOES_NOT_EXIST));
        } else {
            trip = new Trip();
        }
        trip.setTitle(tripApiModel.getTitle());
        trip.setDescription(tripApiModel.getDescription());
        trip.setParticipants(participants);
        trip.setPricePerPerson(tripApiModel.getPricePerSingleParticipant());
        trip.setMeal(tripApiModel.isMeal());
        trip.setMealPricePerPerson(tripApiModel.getMealPricePerPerson());
        trip.setDepartureLocation(tripApiModel.getDepartureLocation());
        trip.setTripLocation(tripApiModel.getTripLocation());
        trip.setStartDate(tripApiModel.getStartDate());
        trip.setEndDate(tripApiModel.getEndDate());
        trip.setTransport(tripApiModel.getFormOfTransport());
        trip.setMainImgUrl(tripApiModel.getMainImageUrl());
        trip.setMinPrice(calculateMinPrice(tripApiModel));
        if (tripApiModel.getGuideId() != null) {
            User user = usersRepository.findById(tripApiModel.getGuideId()).orElseThrow(() -> new TripsBookingException(ExceptionCodes.USER_DOES_NOT_EXIST));
            if (!"ROLE_TRIPS_GUIDE".equals(user.getRole().getName())) {
                throw new TripsBookingException(ExceptionCodes.USER_IS_NOT_GUIDE);
            }
            checkGuideAvailability(trip, tripApiModel.getGuideId());
            trip.setGuide(user);
        }
        trip.getTripImagesList().clear();
        List<TripImages> tripImages = tripApiModel.getOtherImagesUrl().stream().map(img -> new TripImages(img, trip)).collect(Collectors.toList());
        trip.getTripImagesList().addAll(tripImages);

        return trip;
    }

    private BigDecimal calculateMinPrice(TripApiModel tripApiModel) {
        BigDecimal minParticipants = new BigDecimal(tripApiModel.getParticipants().stream().mapToInt(v -> v).min().orElseThrow());
        return tripApiModel.getPricePerSingleParticipant().multiply(minParticipants);
    }

    public void checkGuideAvailability(Trip trip, Integer guideId) {
        List<Trip> guideTrips = tripRepository.findByGuideId(guideId);
        List<AbstractMap.SimpleEntry<Date, Date>> startEndDatePairList = guideTrips.stream().map(a -> new AbstractMap.SimpleEntry<>(a.getStartDate(), a.getEndDate())).collect(Collectors.toList());
        boolean blockingDates = startEndDatePairList.stream().anyMatch(e -> !e.getKey().after(trip.getEndDate()) && !trip.getStartDate().after(e.getValue()));
        if (blockingDates) {
            throw new TripsBookingException(ExceptionCodes.GUIDE_IS_NOT_AVAILABLE);
        }
    }
}
