package pl.us.tripsbooking.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private Integer errorCode;
}
