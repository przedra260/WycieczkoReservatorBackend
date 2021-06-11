package pl.us.tripsbooking.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.us.tripsbooking.security.utils.Base64PasswordEncoder;

@RestController
@RequestMapping("/test")
public class TestRestController {

    @GetMapping("/generate-password")
    public ResponseEntity<?> generateEncodedPassword(@RequestParam String password) {
        Base64PasswordEncoder passwordEncoder = new Base64PasswordEncoder();
        return ResponseEntity.ok(passwordEncoder.encode(password));
    }
}
