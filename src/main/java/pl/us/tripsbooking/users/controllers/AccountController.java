package pl.us.tripsbooking.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.us.tripsbooking.users.dto.ChangePasswordReq;
import pl.us.tripsbooking.users.services.UsersService;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UsersService usersService;

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordReq request) {
        usersService.changePassword(request);
        return ResponseEntity.ok().build();
    }
}
