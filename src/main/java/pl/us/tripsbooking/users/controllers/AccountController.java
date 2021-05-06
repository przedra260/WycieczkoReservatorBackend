package pl.us.tripsbooking.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.us.tripsbooking.users.dto.ChangePasswordReq;
import pl.us.tripsbooking.users.dto.CreateAccountReq;
import pl.us.tripsbooking.users.dto.RemindPasswordReq;
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

    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountReq request) {
        usersService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/remind-password")
    public ResponseEntity<String> remindPassword(@Valid @RequestBody RemindPasswordReq request) {
        return ResponseEntity.ok(usersService.remindPassword(request));
    }
}
