package pl.us.tripsbooking.trips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.us.tripsbooking.trips.repositories.TripRepository;

@Component
public class TripService {

    @Autowired
    private TripRepository tripRepository;


}
