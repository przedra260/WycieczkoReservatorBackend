package pl.us.tripsbooking.trips.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.trips.entities.TripImages;

@Repository
public interface TripImagesRepository extends CrudRepository<TripImages, Integer> {
}
