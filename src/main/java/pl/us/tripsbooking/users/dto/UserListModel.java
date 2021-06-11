package pl.us.tripsbooking.users.dto;

import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.users.entities.Role;

@Getter
@Setter
public class UserListModel {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private boolean isBlocked;
    private boolean isForcePasswordChange;

    public UserListModel(Integer id, String email, String firstName, String lastName, Role role, boolean isBlocked, boolean isForcePasswordChange) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.isBlocked = isBlocked;
        this.isForcePasswordChange = isForcePasswordChange;
    }
}
