package pl.us.tripsbooking.trips.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.trips.entities.Trip;

@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {

}
