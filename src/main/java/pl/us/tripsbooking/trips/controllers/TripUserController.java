package pl.us.tripsbooking.trips.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.us.tripsbooking.security.model.CustomSecurityUser;
import pl.us.tripsbooking.trips.dto.PaidTripModel;
import pl.us.tripsbooking.trips.dto.TripBookingModel;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.services.TripUserReservationService;
import pl.us.tripsbooking.users.dto.UserListModel;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripUserController {

    @Autowired
    private TripUserReservationService tripUserReservationService;

    @PostMapping("/book")
    public ResponseEntity<String> tripBooking(@RequestBody TripBookingModel tripBookingModel, Principal principal) {
        tripUserReservationService.bookTrip(principal.getName(), tripBookingModel);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/book/cancel")
    public ResponseEntity<String> cancelTripReservation(@RequestParam Integer reservationId) {
        tripUserReservationService.cancelTripReservation(reservationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/participants")
    public ResponseEntity<List<UserListModel>> getTripParticipants(@RequestParam Integer tripId) {
        return ResponseEntity.ok(tripUserReservationService.getTripParticipant(tripId));
    }

    @GetMapping("/bookedTrips")
    public ResponseEntity<List<TripListModel>> getBookedTrips(Authentication authentication) {
        CustomSecurityUser userPrincipal = (CustomSecurityUser) authentication.getPrincipal();
        return ResponseEntity.ok(tripUserReservationService.getBookedTrips(userPrincipal.getId()));
    }

    @GetMapping("/paid")
    public ResponseEntity<List<PaidTripModel>> getPaidTrips(Authentication authentication) {
        CustomSecurityUser userPrincipal = (CustomSecurityUser) authentication.getPrincipal();
        return ResponseEntity.ok(tripUserReservationService.getUserPaidTrips(userPrincipal.getId()));
    }
}
