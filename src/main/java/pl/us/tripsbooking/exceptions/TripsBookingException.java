package pl.us.tripsbooking.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TripsBookingException extends RuntimeException {
    private HttpStatus status;
    private Integer errorCode;

    public TripsBookingException(ExceptionCodes exceptionCode) {
        super(exceptionCode.name());
        this.status = exceptionCode.getHttpStatus();
        this.errorCode = exceptionCode.getErrorCode();
    }
}
