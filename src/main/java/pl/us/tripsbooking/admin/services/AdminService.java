package pl.us.tripsbooking.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.us.tripsbooking.admin.dto.ChangeRoleReq;
import pl.us.tripsbooking.admin.dto.UserSettings;
import pl.us.tripsbooking.admin.dto.UsersList;
import pl.us.tripsbooking.admin.enums.ChangeableParam;
import pl.us.tripsbooking.exceptions.ExceptionCodes;
import pl.us.tripsbooking.exceptions.TripsBookingException;
import pl.us.tripsbooking.users.entities.Role;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.enums.RoleEnum;
import pl.us.tripsbooking.users.repositories.RolesRepository;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

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

    public void changeUserRole(ChangeRoleReq request, Integer id) {
        User user = getUserById(id);

        List<Role> roles = new ArrayList<>();
        rolesRepository.findAll().forEach(roles::add);

        Role newRole = roles.stream()
                            .filter(x -> x.getName().equalsIgnoreCase(request.getNewRole().name()))
                            .findFirst()
                            .orElseThrow(() -> new TripsBookingException(ExceptionCodes.ROLE_DOES_NOT_EXIST));
        user.setRole(newRole);
        usersRepository.save(user);
    }

    public void changeState(Integer id, boolean state, ChangeableParam param) {
        User user = getUserById(id);
        switch (param) {
            case BLOCKED: user.setBlocked(state); break;
            case CREDENTIALS_EXPIRED: user.setCredentialsExpired(state); break;
        }
        usersRepository.save(user);
    }

    private User getUserById(Integer id) {
        return usersRepository.findById(id)
                              .orElseThrow(() -> new TripsBookingException(ExceptionCodes.SUCH_ACCOUNT_DOES_NOT_EXIST));
    }
}
