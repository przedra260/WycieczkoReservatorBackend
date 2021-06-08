package pl.us.tripsbooking.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.us.tripsbooking.users.entities.Role;

@Repository
public interface RolesRepository extends CrudRepository<Role, Integer> {}
