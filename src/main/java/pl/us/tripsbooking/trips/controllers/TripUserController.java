package pl.us.tripsbooking.trips.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.us.tripsbooking.trips.services.TripUserService;

import java.security.Principal;

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
}
