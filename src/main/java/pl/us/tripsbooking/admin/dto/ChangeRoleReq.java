package pl.us.tripsbooking.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.us.tripsbooking.users.enums.RoleEnum;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ChangeRoleReq {
    @NotNull(message = "NEW_ROLE_CANNOT_BE_EMPTY")
    private RoleEnum newRole;
}
