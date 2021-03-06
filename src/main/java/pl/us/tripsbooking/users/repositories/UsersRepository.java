package pl.us.tripsbooking.users.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.us.tripsbooking.users.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * " +
                   "  FROM users INNER JOIN roles ON users.role_id=roles.id " +
                   " WHERE roles.name = 'ROLE_TRIPS_GUIDE' " +
                   "   AND users.is_blocked = false", nativeQuery = true)
    List<User> getAllGuides();

    @Query(value = "SELECT guide_id " +
            "  FROM trips " +
            " WHERE start_date <= ?2" +
            "   AND ?1 <= end_date", nativeQuery = true)
    List<Integer> getUnavailableGuidesId(Date startDate, Date endDate);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users " +
                   "   SET balance = COALESCE(balance, 0) + ?1 " +
                   " WHERE id = ?2")
    void rechargeBalance(Integer amount, Integer userId);

    Optional<User> findById(Integer id);
}
