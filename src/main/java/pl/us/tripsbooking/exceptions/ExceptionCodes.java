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
    USER_IS_NOT_GUIDE(2003, HttpStatus.UNPROCESSABLE_ENTITY);


    private Integer errorCode;
    private HttpStatus httpStatus;

    ExceptionCodes(Integer errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
