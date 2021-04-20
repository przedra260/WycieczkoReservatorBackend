package pl.us.tripsbooking.trips.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.us.tripsbooking.trips.dto.TripListModel;
import pl.us.tripsbooking.trips.services.TripService;

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
}
