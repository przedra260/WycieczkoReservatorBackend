package pl.us.tripsbooking.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.us.tripsbooking.trips.services.TripUserReservationService;

@Component
public class RepetitiveTasks {
    @Autowired
    private TripUserReservationService tripUserReservationService;

    @Scheduled(cron = "0 0 * * * *")
    public void markTripsAsHistoric() {
        tripUserReservationService.markTripsAsHistorical();
    }
}
