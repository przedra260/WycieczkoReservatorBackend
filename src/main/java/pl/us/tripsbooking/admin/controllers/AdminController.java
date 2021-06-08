package pl.us.tripsbooking.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.us.tripsbooking.admin.dto.UsersList;
import pl.us.tripsbooking.admin.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<UsersList> getUsersList() {
        return ResponseEntity.ok(adminService.getUsersList());
    }
}
