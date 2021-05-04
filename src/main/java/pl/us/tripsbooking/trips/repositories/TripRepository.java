package pl.us.tripsbooking.trips.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.trips.entities.Trip;

@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {

    @Modifying
    @Query(value = "UPDATE trips SET guide_id = ?2 WHERE id = ?1", nativeQuery = true)
    void assignGuide(Integer tripId, Integer guideId);
}
