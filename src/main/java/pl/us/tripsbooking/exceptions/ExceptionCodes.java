package pl.us.tripsbooking.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCodes {
    //changing password (1xxx)
    EMAIL_DOES_NOT_EXIST(1001, HttpStatus.NOT_FOUND),
    OLD_PASSWORD_DOES_NOT_MATCH(1002, HttpStatus.UNPROCESSABLE_ENTITY),
    NEW_PASSWORD_IS_TO_SHORT(1003, HttpStatus.UNPROCESSABLE_ENTITY),
    PASSWORDS_ARE_EQUALS(1004, HttpStatus.UNPROCESSABLE_ENTITY),
    //trip operation(2xxx)
    USER_DOES_NOT_EXIST(2001, HttpStatus.NOT_FOUND),
    TRIP_DOES_NOT_EXIST(2002, HttpStatus.NOT_FOUND),
    USER_IS_NOT_GUIDE(2003, HttpStatus.UNPROCESSABLE_ENTITY),
    //balance operations(3xxx)
    AMOUNT_HAS_TO_BE_POSITIVE(3001, HttpStatus.UNPROCESSABLE_ENTITY),
    //creating account(4xxx)
    EMAIL_CANNOT_BE_NULL(4001, HttpStatus.UNPROCESSABLE_ENTITY),
    EMAIL_NOT_VALID(4002, HttpStatus.UNPROCESSABLE_ENTITY),
    EMAIL_HAS_1_TO_45_CHARS(4003, HttpStatus.UNPROCESSABLE_ENTITY),
    PASSWORD_CANNOT_BE_NULL(4004, HttpStatus.UNPROCESSABLE_ENTITY),
    PASSWORD_HAS_MIN_6_CHARS(4005, HttpStatus.UNPROCESSABLE_ENTITY),
    FIRST_NAME_CANNOT_BE_NULL(4006, HttpStatus.UNPROCESSABLE_ENTITY),
    FIRST_NAME_HAS_1_TO_45_CHARS(4007, HttpStatus.UNPROCESSABLE_ENTITY),
    LAST_NAME_CANNOT_BE_NULL(4008, HttpStatus.UNPROCESSABLE_ENTITY),
    LAST_NAME_HAS_1_TO_45_CHARS(4009, HttpStatus.UNPROCESSABLE_ENTITY),
    STREET_AND_NUMBER_CANNOT_BE_NULL(4010, HttpStatus.UNPROCESSABLE_ENTITY),
    STREET_AND_NUMBER_HAS_1_TO_100_CHARS(4011, HttpStatus.UNPROCESSABLE_ENTITY),
    ZIP_CODE_CANNOT_BE_NULL(4012, HttpStatus.UNPROCESSABLE_ENTITY),
    ZIP_CODE_HAS_6_CHARS(4013, HttpStatus.UNPROCESSABLE_ENTITY),
    CITY_CANNOT_BE_NULL(4014, HttpStatus.UNPROCESSABLE_ENTITY),
    CITY_HAS_1_TO_45_CHARS(4015, HttpStatus.UNPROCESSABLE_ENTITY),
    PHONE_NUMBER_CANNOT_BE_NULL(4016, HttpStatus.UNPROCESSABLE_ENTITY),
    PHONE_NUMBER_HAS_7_TO_15_CHARS(4017, HttpStatus.UNPROCESSABLE_ENTITY),
    ANSWER_CANNOT_BE_NULL(4018, HttpStatus.UNPROCESSABLE_ENTITY),
    ANSWER_HAS_1_TO_100_CHARS(4019, HttpStatus.UNPROCESSABLE_ENTITY),
    QUESTION_CANNOT_BE_NULL(4020, HttpStatus.UNPROCESSABLE_ENTITY),
    QUESTION_HAS_TO_BE_1_TO_3(4021, HttpStatus.UNPROCESSABLE_ENTITY),
    USER_ALREADY_EXISTS(4022, HttpStatus.CONFLICT);

    private Integer errorCode;
    private HttpStatus httpStatus;

    ExceptionCodes(Integer errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public static ExceptionCodes getByName(String name) {
        for (ExceptionCodes e : ExceptionCodes.values())
            if (e.name().equals(name))
                return e;
        return null;
    }
}
