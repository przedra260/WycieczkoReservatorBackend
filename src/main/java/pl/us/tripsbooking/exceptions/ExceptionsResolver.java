package pl.us.tripsbooking.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TripsBookingException.class)
    public ResponseEntity<ErrorResponse> handleTripsBookingExceptions(TripsBookingException exc) {
        return ResponseEntity.status(exc.getStatus())
                             .body(ErrorResponse.builder()
                                                .errorCode(exc.getErrorCode())
                                                .message(exc.getMessage())
                                                .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exc,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String errorMessage = exc.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ExceptionCodes exCode = ExceptionCodes.getByName(errorMessage);
        return ResponseEntity.status(exCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .errorCode(exCode.getErrorCode())
                        .message(exCode.name())
                        .build());
    }
}
