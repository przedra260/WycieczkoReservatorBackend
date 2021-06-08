package pl.us.tripsbooking.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.users.enums.RoleEnum;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UsersList {
    private List<UserSettings> userSettings;
    private List<RoleEnum> availableRoles;
}
