package pl.us.tripsbooking.users.mappers;

import org.springframework.stereotype.Component;
import pl.us.tripsbooking.users.dto.CreateAccountReq;
import pl.us.tripsbooking.users.dto.UserListModel;
import pl.us.tripsbooking.users.dto.UserModel;
import pl.us.tripsbooking.users.entities.Role;
import pl.us.tripsbooking.users.entities.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public List<UserListModel> mapToUserListModel(List<User> userList) {
        return userList.stream().map(user -> new UserListModel(user.getId(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getRole(), user.isBlocked(), user.isCredentialsExpired())).collect(Collectors.toList());
    }

    public UserModel mapToUserModel(User user) {
        return new UserModel(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getBalance());
    }

    public User fromCreateAccountReq(CreateAccountReq req) {
        return User.builder()
                   .balance(0)
                   .city(req.getCity())
                   .credentialsExpired(false)
                   .email(req.getEmail())
                   .firstName(req.getFirstName())
                   .isBlocked(false)
                   .lastName(req.getLastName())
                   .password(req.getPassword())
                   .passwordHelpQuestionAnswer(req.getPasswordHelpQuestionAnswer())
                   .phoneNumber(req.getPhoneNumber())
                   .selectedPasswordHelpQuestion(req.getSelectedPasswordHelpQuestion())
                   .streetAndNumber(req.getStreetAndNumber())
                   .zipCode(req.getZipCode())
                   .role(new Role(2))
                   .build();
    }
}
