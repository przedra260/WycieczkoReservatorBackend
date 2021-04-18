package pl.us.tripsbooking.users.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.users.entities.User;


@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {

    @Query(value = "select * from users where users.login = ?1", nativeQuery = true)
    User getUserInfo(String login);

}
