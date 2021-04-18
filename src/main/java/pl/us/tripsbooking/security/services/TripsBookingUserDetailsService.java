package pl.us.tripsbooking.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.util.Arrays;

@Service
@Qualifier("userDetailService")
public class TripsBookingUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userInfo = usersRepository.getUserInfo(s);

        if(userInfo == null)
            throw new UsernameNotFoundException("User not found");

        return new org.springframework.security.core.userdetails.User(userInfo.getLogin(), userInfo.getPassword(), Arrays.asList(new SimpleGrantedAuthority(userInfo.getRole().getName())));
    }
}
