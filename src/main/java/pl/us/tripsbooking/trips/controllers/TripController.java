package pl.us.tripsbooking.trips.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.us.tripsbooking.trips.dto.BookedTripApiModel;
import pl.us.tripsbooking.trips.dto.BookedTripListModel;
import pl.us.tripsbooking.trips.dto.TripApiModel;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.services.TripService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/getAllTrips")
    public ResponseEntity<List<TripListModel>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping("/getUserTrips")
    public ResponseEntity<List<BookedTripListModel>> getUserTrips(Principal principal) {
        return ResponseEntity.ok(tripService.getUserTrips(principal.getName()));
    }

    @GetMapping("/getGuideTrips")
    public ResponseEntity<List<TripListModel>> getGuideTrips(Principal principal) {
        return ResponseEntity.ok(tripService.getGuideTrips(principal.getName()));
    }

    @GetMapping("/getTripDetails")
    public ResponseEntity<TripApiModel> getTripDetails(@RequestParam Integer tripId) {
        return ResponseEntity.ok(tripService.getTripDetails(tripId));
    }

    @GetMapping("/getBookedTripDetails")
    public ResponseEntity<BookedTripApiModel> getBookedTripDetails(@RequestParam Integer reservationId) {
        return ResponseEntity.ok(tripService.getBookedTripDetails(reservationId));
    }

    @PostMapping("/saveTrip")
    public ResponseEntity<String> saveTrip(@RequestBody TripApiModel tripApiModel) {
        tripService.saveTrip(tripApiModel);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteTrip")
    public ResponseEntity<String> deleteTrip(@RequestParam Integer tripId) {
        tripService.deleteTrip(tripId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/assignGuide")
    public ResponseEntity<String> assignGuide(@RequestParam Integer tripId, @RequestParam Integer guideId) {
        tripService.assignGuide(tripId, guideId);
        return ResponseEntity.ok().build();
    }

}
