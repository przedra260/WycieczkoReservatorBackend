package pl.us.tripsbooking.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.repositories.UsersRepository;

@Service
@Qualifier("usersService")
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public User getUserInfo(String s){
        return usersRepository.findByEmail(s);
    }
}
