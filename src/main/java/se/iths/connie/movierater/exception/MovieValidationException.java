package se.iths.connie.movierater.exception;

public class MovieValidationException extends RuntimeException {

    public MovieValidationException(String message) {
        super(message);
    }

    public MovieValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}