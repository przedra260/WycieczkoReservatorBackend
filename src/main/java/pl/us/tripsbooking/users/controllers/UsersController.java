package pl.us.tripsbooking.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/getUserInfo")
    public ResponseEntity<User> getUserInfo(@RequestParam String username) {
        User user = usersService.getUserInfo(username);
        return ResponseEntity.ok(user);
    }
}
