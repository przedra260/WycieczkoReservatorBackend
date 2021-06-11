package pl.us.tripsbooking.trips.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.us.tripsbooking.trips.entities.TripUserReservation;

import java.util.List;


@Repository
public interface TripUserRepository extends CrudRepository<TripUserReservation, Integer> {

    @Modifying
    @Query(value = "UPDATE trips_users_reservation SET is_canceled = true WHERE id = ?1", nativeQuery = true)
    void cancelTripReservation(Integer reservationId);

    @Query(value = "SELECT * FROM trips_users_reservation where trip_id = ?1", nativeQuery = true)
    List<TripUserReservation> findByTripId(Integer tripId);

    @Query(value = "SELECT * FROM trips_users_reservation where user_id = ?1 and is_canceled != true and is_historic != true", nativeQuery = true)
    List<TripUserReservation> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM trips_users_reservation where user_id = ?1 and price is not null order by id desc", nativeQuery = true)
    List<TripUserReservation> findPaidTripsByUserIdOrderedDesc(Integer userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE trips_users_reservation tur " +
                   "   SET is_historic = 1 " +
                   " WHERE is_historic = 0 " +
                   "   AND EXISTS (SELECT 1 " +
                   "                 FROM trips " +
                   "                WHERE id = tur.trip_id " +
                   "                  AND start_date < NOW())",
            nativeQuery = true)
    void markTripsAsHistorical();
}
