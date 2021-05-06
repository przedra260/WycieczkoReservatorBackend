package pl.us.tripsbooking.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordReq {
    @NotNull(message = "EMAIL_DOES_NOT_EXIST")
    private String email;

    @NotNull(message = "OLD_PASSWORD_DOES_NOT_MATCH")
    private String oldPassword;

    @NotNull(message = "NEW_PASSWORD_IS_TO_SHORT")
    @Size(min = 6, message = "NEW_PASSWORD_IS_TO_SHORT")
    private String newPassword;
}
