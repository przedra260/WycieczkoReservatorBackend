package pl.us.tripsbooking.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.us.tripsbooking.admin.dto.ChangeRoleReq;
import pl.us.tripsbooking.admin.dto.UsersList;
import pl.us.tripsbooking.admin.enums.ChangeableParam;
import pl.us.tripsbooking.admin.services.AdminService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<UsersList> getUsersList() {
        return ResponseEntity.ok(adminService.getUsersList());
    }

    @PutMapping("/users/{id}/change-role")
    public ResponseEntity<Void> changeUserRole(@PathVariable Integer id,
                                               @Valid @RequestBody ChangeRoleReq request) {
        adminService.changeUserRole(request, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/users/{id}/lock-account")
    public ResponseEntity<Void> lockAccount(@PathVariable Integer id) {
        adminService.changeState(id, true, ChangeableParam.BLOCKED);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/users/{id}/unlock-account")
    public ResponseEntity<Void> unlockAccount(@PathVariable Integer id) {
        adminService.changeState(id, false, ChangeableParam.BLOCKED);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/users/{id}/enforce-changing-password")
    public ResponseEntity<Void> enforceChangingPassword(@PathVariable Integer id) {
        adminService.changeState(id, true, ChangeableParam.CREDENTIALS_EXPIRED);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/users/{id}/cancel-changing-password")
    public ResponseEntity<Void> cancelChangingPassword(@PathVariable Integer id) {
        adminService.changeState(id, false, ChangeableParam.CREDENTIALS_EXPIRED);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
