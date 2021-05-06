package pl.us.tripsbooking.security.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

public class Base64PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return new String(Base64.getEncoder().encode(charSequence.toString().getBytes()));
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(new String(Base64.getDecoder().decode(s)));
    }

    public String decode(String password) {
        return new String(Base64.getDecoder().decode(password));
    }
}
