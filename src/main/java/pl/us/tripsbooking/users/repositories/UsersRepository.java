package pl.us.tripsbooking.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.users.entities.User;


@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
