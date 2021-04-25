package pl.us.tripsbooking.trips.mappers;

import org.springframework.stereotype.Component;
import pl.us.tripsbooking.trips.dto.TripApiModel;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.entities.Trip;
import pl.us.tripsbooking.trips.entities.TripImages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripMapper {

    public List<TripListModel> mapToTripListModel(List<Trip> tripList) {
        return tripList.stream().map(trip -> new TripListModel(trip.getId(), trip.getTitle(), trip.getMainImgUrl(), trip.getMinPrice())).collect(Collectors.toList());
    }

    public TripApiModel mapToTripApiModel(Trip trip) {
        List<Integer> participants = Arrays.stream(trip.getParticipants().split(",")).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> rooms = Arrays.stream(trip.getRoomSizes().split(",")).map(Integer::valueOf).collect(Collectors.toList());
        List<String> urls = trip.getTripImagesList().stream().map(TripImages::getImgUrl).collect(Collectors.toList());

        return new TripApiModel(
                trip.getId(),
                trip.getTitle(),
                trip.getDescription(),
                participants,
                trip.getPricePerPerson(),
                rooms,
                trip.getPricePerRoom(),
                trip.isMeal(),
                trip.getPricePerDailyMeal(),
                trip.getDepartureLocation(),
                trip.getTripLocation(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getTransport(),
                trip.getMainImgUrl(),
                urls
        );
    }

    public Trip mapToTrip(TripApiModel tripApiModel) {
        String participants = tripApiModel.getParticipants().stream().map(Object::toString).collect(Collectors.joining(", "));
        String rooms = tripApiModel.getRoomSizes().stream().map(Object::toString).collect(Collectors.joining(", "));

        Trip trip = new Trip();
        trip.setTitle(tripApiModel.getTitle());
        trip.setDescription(tripApiModel.getDescription());
        trip.setParticipants(participants);
        trip.setPricePerPerson(tripApiModel.getPricePerSingleParticipant());
        trip.setRoomSizes(rooms);
        trip.setPricePerRoom(tripApiModel.getPricePerSingleRoom());
        trip.setMeal(tripApiModel.isMeal());
        trip.setPricePerDailyMeal(tripApiModel.getPricePerSingleDayOfMeals());
        trip.setDepartureLocation(tripApiModel.getDepartureLocation());
        trip.setTripLocation(tripApiModel.getTripLocation());
        trip.setStartDate(tripApiModel.getStartDate());
        trip.setEndDate(tripApiModel.getEndDate());
        trip.setTransport(tripApiModel.getFormOfTransport());

        List<TripImages> tripImages = tripApiModel.getOtherImagesUrl().stream().map(url -> new TripImages(url, trip)).collect(Collectors.toList());

        trip.setTripImagesList(tripImages);

        return trip;
    }
}
