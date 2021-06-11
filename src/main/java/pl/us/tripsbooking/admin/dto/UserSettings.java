package pl.us.tripsbooking.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.us.tripsbooking.users.enums.RoleEnum;

@Getter
@Setter
@Builder
public class UserSettings {
    private Integer id;
    private String email;
    private boolean isBlocked;
    private boolean credentialsExpired;
    private RoleEnum role;
}
