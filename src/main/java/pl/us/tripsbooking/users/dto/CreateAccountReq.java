package pl.us.tripsbooking.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class CreateAccountReq {
    @NotNull(message = "EMAIL_CANNOT_BE_NULL")
    @Email(message = "EMAIL_NOT_VALID")
    @Size(min = 1, max = 45, message = "EMAIL_HAS_1_TO_45_CHARS")
    private String email;

    @NotNull(message = "PASSWORD_CANNOT_BE_NULL")
    @Size(min = 6, message = "PASSWORD_HAS_MIN_6_CHARS")
    private String password;

    @NotNull(message = "FIRST_NAME_CANNOT_BE_NULL")
    @Size(min = 1, max = 45, message = "FIRST_NAME_HAS_1_TO_45_CHARS")
    private String firstName;

    @NotNull(message = "LAST_NAME_CANNOT_BE_NULL")
    @Size(min = 1, max = 45, message = "LAST_NAME_HAS_1_TO_45_CHARS")
    private String lastName;

    @NotNull(message = "STREET_AND_NUMBER_CANNOT_BE_NULL")
    @Size(min = 1, max = 100, message = "STREET_AND_NUMBER_HAS_1_TO_100_CHARS")
    private String streetAndNumber;

    @NotNull(message = "ZIP_CODE_CANNOT_BE_NULL")
    @Size(min = 6, max = 6, message = "ZIP_CODE_HAS_6_CHARS")
    private String zipCode;

    @NotNull(message = "CITY_CANNOT_BE_NULL")
    @Size(min = 1, max = 45, message = "CITY_HAS_1_TO_45_CHARS")
    private String city;

    @NotNull(message = "PHONE_NUMBER_CANNOT_BE_NULL")
    @Size(min = 7, max = 15, message = "PHONE_NUMBER_HAS_7_TO_15_CHARS")
    private String phoneNumber;

    @NotNull(message = "ANSWER_CANNOT_BE_NULL")
    @Size(min = 1, max = 100, message = "ANSWER_HAS_1_TO_100_CHARS")
    private String passwordHelpQuestionAnswer;

    @NotNull(message = "QUESTION_CANNOT_BE_NULL")
    @Min(value = 1, message = "QUESTION_HAS_TO_BE_1_TO_3")
    @Max(value = 3, message = "QUESTION_HAS_TO_BE_1_TO_3")
    private Integer selectedPasswordHelpQuestion;
}
