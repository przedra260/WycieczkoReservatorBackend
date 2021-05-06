package pl.us.tripsbooking.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class RemindPasswordReq {
    @NotNull(message = "EMAIL_FOR_REMIND_PASSWORD_CANNOT_BE_NULL")
    private String email;
    @NotNull(message = "ANSWER_FOR_REMIND_PASSWORD_CANNOT_BE_NULL")
    private String answer;
}
