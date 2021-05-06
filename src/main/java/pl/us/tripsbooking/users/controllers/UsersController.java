package pl.us.tripsbooking.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.us.tripsbooking.exceptions.ExceptionCodes;
import pl.us.tripsbooking.exceptions.TripsBookingException;
import pl.us.tripsbooking.security.model.CustomSecurityUser;
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

    @GetMapping("/getAllGuides")
    public ResponseEntity<List<UserListModel>> getAllGuides() {
        return ResponseEntity.ok(usersService.getAllGuides());
    }

    @PutMapping("/recharge-balance/{amount}")
    public ResponseEntity<Void> rechargeBalance(@PathVariable Integer amount,
                                                Authentication authentication) {
        if (amount < 1)
            throw new TripsBookingException(ExceptionCodes.AMOUNT_HAS_TO_BE_POSITIVE);

        usersService.rechargeBalance(amount, ((CustomSecurityUser) authentication.getPrincipal()).getId());
        return ResponseEntity.ok().build();
    }
}
