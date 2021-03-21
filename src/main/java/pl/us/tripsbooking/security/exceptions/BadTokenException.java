package pl.us.tripsbooking.security.exceptions;

public class BadTokenException extends Exception {

    public BadTokenException(String message){
        super(message);
    }

    public String getMessage(){
        return super.getMessage();
    }
}