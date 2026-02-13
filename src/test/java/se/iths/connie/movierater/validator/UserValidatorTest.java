package se.iths.connie.movierater.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.connie.movierater.exception.MissingRequiredFieldException;
import se.iths.connie.movierater.exception.ValidationException;
import se.iths.connie.movierater.model.User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserValidatorTest {

    private UserValidator userValidator;
    private User user;


    @BeforeEach
    public void setUp() {
        userValidator = new UserValidator();
        user = new User();

        String validUsername = "a".repeat(4);
        String validPassword = "a".repeat(8);
        String validEmail = "armin@example.com";

        user.setUsername(validUsername);
        user.setPassword(validPassword);
        user.setEmail(validEmail);

    }

    @Test
    public void testUserIsValid() {
        assertDoesNotThrow(() -> userValidator.validateUser(user));
    }

    @Test
    public void testExceptionIfUserIsNull() {
        assertThrows(ValidationException.class, () -> userValidator.validateUser(null));
    }


    @Test
    public void testExceptionIfPasswordBlank() {
        user.setPassword("");

        assertThrows(MissingRequiredFieldException.class, () -> {
            userValidator.validateUser(user);
        });
    }

    @Test
    public void testExceptionIfPasswordIsNull() {
        user.setPassword(null);

        assertThrows(MissingRequiredFieldException.class, () -> userValidator.validateUser(user));
    }

    @Test
    public void testExceptionIfPasswordTooShort() {
        user.setPassword("a".repeat(7));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateUser(user);
        });
    }

    @Test
    public void testExceptionIfUsernameBlank() {
        user.setUsername("");

        assertThrows(MissingRequiredFieldException.class, () -> {
            userValidator.validateUser(user);
        });
    }

    @Test
    public void testExceptionIfUsernameIsNull() {
        user.setUsername(null);

        assertThrows(MissingRequiredFieldException.class, () -> {
            userValidator.validateUser(user);
        });
    }

    @Test
    public void testExceptionIfUsernameTooShort() {
        user.setUsername("a".repeat(3));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateUser(user);
        });
    }

    @Test
    public void testExceptionIfUsernameTooLong() {
        user.setUsername("a".repeat(21));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateUser(user);
        });
    }

    @Test
    public void testExceptionIfEmailBlank() {
        user.setEmail("");

        assertThrows(MissingRequiredFieldException.class, () -> {
            userValidator.validateUser(user);
        });
    }

    @Test
    public void testExceptionIfEmailIsNull() {
        user.setEmail(null);

        assertThrows(MissingRequiredFieldException.class, () -> {
            userValidator.validateUser(user);
        });
    }

    @Test
    public void testExceptionIfEmailTooLong() {
        user.setEmail("@.".repeat(51));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateUser(user);
        });
    }


    @Test
    public void testExceptionIfEmailMissingAT() {
        user.setEmail("armin.example.com");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateUser(user);
        });

    }

    @Test
    public void testExceptionIfEmailMissingDOT() {
        user.setEmail("armin@example@com");
        assertThrows(ValidationException.class, () -> {
            userValidator.validateUser(user);
        });
    }

}
