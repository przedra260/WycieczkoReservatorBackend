package pl.us.tripsbooking.trips.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.trips.entities.TripUser;

import java.util.List;


@Repository
public interface TripUserRepository extends CrudRepository<TripUser, Integer> {

    @Modifying
    @Query(value = "DELETE FROM trips_users where trip_id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteByTripAndUser(Integer tripId, Integer userId);

    @Query(value = "SELECT * FROM trips_users where trip_id = ?1", nativeQuery = true)
    List<TripUser> findByTripId(Integer tripId);

    @Query(value = "SELECT * FROM trips_users where user_id = ?1", nativeQuery = true)
    List<TripUser> findByUserId(Integer userId);
}
