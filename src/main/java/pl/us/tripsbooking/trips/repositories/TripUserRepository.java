package pl.us.tripsbooking.trips.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.trips.entities.TripUser;

@Repository
public interface TripUserRepository extends CrudRepository<TripUser, Integer> {

}
