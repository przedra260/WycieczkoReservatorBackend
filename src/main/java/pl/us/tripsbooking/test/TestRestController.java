package pl.us.tripsbooking.test;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRestController {

    @GetMapping("/generate-password")
    public ResponseEntity<?> generateEncodedPassword(@RequestParam String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
        return ResponseEntity.ok(passwordEncoder.encode(password));
    }
}
