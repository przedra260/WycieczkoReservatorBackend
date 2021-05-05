package pl.us.tripsbooking.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordReq {
    private String email;
    private String oldPassword;
    private String newPassword;
}
