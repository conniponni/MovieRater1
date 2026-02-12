package se.iths.connie.movierater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.connie.movierater.exception.MovieValidationException;
import se.iths.connie.movierater.model.Movie;
import se.iths.connie.movierater.validator.MovieValidator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieValidatorTest {

    private MovieValidator validator;

    @BeforeEach
    void setup() {
        validator = new MovieValidator();
    }

    @Test
    void validateMovie_validMovie_passes() {
        Movie movie = new Movie("Title", "Genre", 2020, 120, 8.5);
        assertDoesNotThrow(() -> validator.validateMovie(movie));
    }

    @Test
    void validateTitle_empty_throwsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateTitle(""));
    }

    @Test
    void validateGenre_empty_throwsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateGenre(""));
    }

    @Test
    void validateReleaseYear_invalid_throwsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateReleaseYear(1700));
    }

    @Test
    void validateDuration_invalid_throwsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateDurationMinutes(0));
    }

    @Test
    void validateRating_invalid_throwsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateRating(20));
    }
}