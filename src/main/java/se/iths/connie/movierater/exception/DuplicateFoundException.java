package se.iths.connie.movierater.exception;

public class DuplicateFoundException extends RuntimeException {
    public DuplicateFoundException(String message) {
        super(message);
    }
}
