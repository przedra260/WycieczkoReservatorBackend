package pl.us.tripsbooking.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ TripsBookingException.class })
    public ResponseEntity<ErrorResponse> handleTripsBookingExceptions(TripsBookingException tbEx) {
        return ResponseEntity.status(tbEx.getStatus())
                             .body(ErrorResponse.builder()
                                                .errorCode(tbEx.getErrorCode())
                                                .message(tbEx.getMessage())
                                                .build());
    }
}
