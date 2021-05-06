package pl.us.tripsbooking.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.us.tripsbooking.exceptions.ExceptionCodes;
import pl.us.tripsbooking.exceptions.TripsBookingException;
import pl.us.tripsbooking.security.utils.Base64PasswordEncoder;
import pl.us.tripsbooking.users.dto.ChangePasswordReq;
import pl.us.tripsbooking.users.dto.CreateAccountReq;
import pl.us.tripsbooking.users.dto.RemindPasswordReq;
import pl.us.tripsbooking.users.dto.UserListModel;
import pl.us.tripsbooking.users.entities.User;
import pl.us.tripsbooking.users.mappers.UserMapper;
import pl.us.tripsbooking.users.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("usersService")
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    private Base64PasswordEncoder passwordEncoder = new Base64PasswordEncoder();

    public User getUserInfo(String s){
        return usersRepository.findByEmail(s).get();
    }

    public List<UserListModel> getAllUsers() {
        List<User> userList = new ArrayList<>();
        usersRepository.findAll().forEach(userList::add);
        return userMapper.mapToUserListModel(userList);
    }

    public List<UserListModel> getAllGuides() {
        List<User> guidesList = new ArrayList<>(usersRepository.getAllGuides());
        return userMapper.mapToUserListModel(guidesList);
    }

    @Transactional
    public void changePassword(ChangePasswordReq request) {
        Optional<User> userOpt = usersRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty())
            throw new TripsBookingException(ExceptionCodes.EMAIL_DOES_NOT_EXIST);

        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword()))
            throw new TripsBookingException(ExceptionCodes.OLD_PASSWORD_DOES_NOT_MATCH);
        if (request.getNewPassword().equals(request.getOldPassword()))
            throw new TripsBookingException(ExceptionCodes.PASSWORDS_ARE_EQUALS);

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        usersRepository.save(user);
    }

    public void rechargeBalance(Integer amount, Integer userId) {
        usersRepository.rechargeBalance(amount, userId);
    }

    public void createAccount(CreateAccountReq request) {
        if (usersRepository.findByEmail(request.getEmail()).isPresent())
            throw new TripsBookingException(ExceptionCodes.USER_ALREADY_EXISTS);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        usersRepository.save(userMapper.fromCreateAccountReq(request));
    }

    public String remindPassword(RemindPasswordReq request) {
        User user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new TripsBookingException(ExceptionCodes.SUCH_ACCOUNT_DOES_NOT_EXIST));
        if (user.getPasswordHelpQuestionAnswer().equals(request.getAnswer()))
            return passwordEncoder.decode(user.getPassword());
        else
            throw new TripsBookingException(ExceptionCodes.INCORRECT_ANSWER);
    }
}
