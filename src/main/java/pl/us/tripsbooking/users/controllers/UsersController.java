package pl.us.tripsbooking.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.us.tripsbooking.users.dto.UserListModel;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.services.UsersService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/getUserInfo")
    public ResponseEntity<User> getUserInfo(Principal principal) {
        User user = usersService.getUserInfo(principal.getName());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserListModel>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }
}
