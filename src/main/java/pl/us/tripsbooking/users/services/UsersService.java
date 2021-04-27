package pl.us.tripsbooking.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.us.tripsbooking.users.dto.UserListModel;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.mappers.UserMapper;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("usersService")
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    public User getUserInfo(String s){
        return usersRepository.findByEmail(s);
    }

    public List<UserListModel> getAllUsers() {
        List<User> userList = new ArrayList<>();
        usersRepository.findAll().forEach(userList::add);
        return userMapper.mapToUserListModel(userList);
    }
}
