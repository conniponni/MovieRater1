package se.iths.connie.movierater.validator;


import org.springframework.stereotype.Component;
import se.iths.connie.movierater.exception.MissingRequiredFieldException;
import se.iths.connie.movierater.model.User;

@Component
public class UserValidator {


    public void validate(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cant be null");
        }
        validateUsername(user.getUsername());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateUsernameLength(user.getUsername());
        validateEmailLength(user.getEmail());
        validatePasswordLength(user.getPassword());
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new MissingRequiredFieldException("Username cannot be empty");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new MissingRequiredFieldException("Email cannot be empty");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new MissingRequiredFieldException("Password cannot be empty");
        }
    }

    private void validateUsernameLength(String username) {
        if (username.length() < 3) {
            throw new IllegalArgumentException("Username length should be at least 3 characters");
        }
        if (username.length() > 20) {
            throw new IllegalArgumentException("Username length must be at most 20 characters");
        }
    }

    private void validatePasswordLength(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password length should be at least 8 characters");
        }
        if (password.length() > 20) {
            throw new IllegalArgumentException("Password length must be at most 20 characters");
        }
    }

    private void validateEmailLength(String email) {
        if (email.length() > 50) {
            throw new IllegalArgumentException("Email length must be at most 20 characters");
        }

    }
}
