package se.iths.connie.movierater.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.connie.movierater.exception.ValidationException;
import se.iths.connie.movierater.model.Director;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectorValidatorTest {

    private DirectorValidator directorValidator;
    private Director director;

    @BeforeEach
    void setUp() {
        directorValidator = new DirectorValidator();
        director = new Director();
        director.setName("Sam Raimi");
        director.setBirthYear(1959);
        director.setNationality("American");
        director.setNumberOfMoviesDirected(12);
        director.setActive(true);
    }

    @Test
    void validateWorkingNameTest() {

        Assertions.assertDoesNotThrow(() -> directorValidator.validateDirector(director));
    }

    @Test
    void validateNameThrowsExceptionTest() {
        director.setName("");

        assertThrows(ValidationException.class, () -> directorValidator.validateDirector(director));
    }

    @Test
    void validateBirthYearThrowsExceptionTest() {
        director.setBirthYear(1750);

        assertThrows(ValidationException.class, () -> directorValidator.validateDirector(director));
    }

    @Test
    void validateNationalityDoesNotThrowExceptionTest() {

        assertDoesNotThrow(() -> directorValidator.validateDirector(director));
    }

    @Test
    void validateNationalityDoThrowExceptionTest() {
        director.setNationality("");

        assertThrows(ValidationException.class, () -> directorValidator.validateDirector(director));
    }

    @Test
    void validateNumberOfMoviesThrowExceptionTest() {
        director.setNumberOfMoviesDirected(-20);

        assertThrows(ValidationException.class, () -> directorValidator.validateDirector(director));
    }

    @Test
    void validateActiveTodayDoesNotThrowExceptionTest() {

        assertDoesNotThrow(() -> directorValidator.validateDirector(director));
    }
}
