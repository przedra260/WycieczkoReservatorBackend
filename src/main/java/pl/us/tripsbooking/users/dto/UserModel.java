package pl.us.tripsbooking.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer balance;


    public UserModel(Integer id, String email, String firstName, String lastName, Integer balance) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }
}
