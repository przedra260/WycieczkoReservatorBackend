package pl.us.tripsbooking.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.us.tripsbooking.admin.dto.UserSettings;
import pl.us.tripsbooking.admin.dto.UsersList;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.enums.RoleEnum;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private UsersRepository usersRepository;

    public UsersList getUsersList() {
        List<User> users = new ArrayList<>();
        usersRepository.findAll().forEach(users::add);
        List<UserSettings> settings = users.stream().map(x -> UserSettings.builder()
                                                                          .id(x.getId())
                                                                          .credentialsExpired(x.isCredentialsExpired())
                                                                          .isBlocked(x.isBlocked())
                                                                          .email(x.getEmail())
                                                                          .role(RoleEnum.getByName(x.getRole().getName()))
                                                                          .build())
                                                     .collect(Collectors.toList());
        return new UsersList(settings, Arrays.asList(RoleEnum.values()));
    }
}
