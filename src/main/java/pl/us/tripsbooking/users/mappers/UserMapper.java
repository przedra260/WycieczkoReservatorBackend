package pl.us.tripsbooking.users.mappers;

import org.springframework.stereotype.Component;
import pl.us.tripsbooking.users.dto.UserListModel;
import pl.us.tripsbooking.users.entities.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public List<UserListModel> mapToUserListModel(List<User> userList) {
        return userList.stream().map(user -> new UserListModel(user.getId(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getRole(), user.isBlocked(), user.isCredentialsExpired())).collect(Collectors.toList());
    }
}
