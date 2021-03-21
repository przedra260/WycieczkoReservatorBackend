package pl.us.tripsbooking.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.us.tripsbooking.security.model.UserInfo;
import pl.us.tripsbooking.security.repositories.LoginRepository;

import java.util.Arrays;

@Service
@Qualifier("userDetailService")
public class TripsBookingUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = loginRepository.findUserLoginInfoByUsername(s);

        if(userInfo == null)
            throw new UsernameNotFoundException("User not found");

        return new User(userInfo.getLogin(), userInfo.getPassword(), Arrays.asList(new SimpleGrantedAuthority(userInfo.getRole())));
    }
}
