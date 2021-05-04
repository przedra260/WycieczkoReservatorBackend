package pl.us.tripsbooking.trips.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.us.tripsbooking.security.model.CustomSecurityUser;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.services.TripUserService;
import pl.us.tripsbooking.users.dto.UserListModel;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripUserController {

    @Autowired
    private TripUserService tripUserService;

    @GetMapping("/book")
    public ResponseEntity<String> tripBooking(@RequestParam Integer tripId, Principal principal) {
        tripUserService.bookTrip(principal.getName(), tripId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/book/cancel")
    public ResponseEntity<String> cancelTripBooking(Authentication authentication, @RequestParam Integer tripId) {
        CustomSecurityUser userPrincipal = (CustomSecurityUser) authentication.getPrincipal();
        tripUserService.cancelTripBooking(tripId, userPrincipal.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/participants")
    public ResponseEntity<List<UserListModel>> getTripParticipants(@RequestParam Integer tripId) {
        return ResponseEntity.ok(tripUserService.getTripParticipant(tripId));
    }

    @GetMapping("/bookedTrips")
    public ResponseEntity<List<TripListModel>> getBookedTrips(Authentication authentication) {
        CustomSecurityUser userPrincipal = (CustomSecurityUser) authentication.getPrincipal();
        return ResponseEntity.ok(tripUserService.getBookedTrips(userPrincipal.getId()));
    }
}
