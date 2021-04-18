package pl.us.tripsbooking.users.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.users.entities.Users;


@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    @Query(value = "select * from users where users.login = ?1", nativeQuery = true)
    Users getUserInfo(String login);

}
