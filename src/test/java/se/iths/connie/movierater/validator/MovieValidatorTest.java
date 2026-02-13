package se.iths.connie.movierater.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.connie.movierater.exception.MovieValidationException;
import se.iths.connie.movierater.model.Movie;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieValidatorTest {

    private MovieValidator validator;

    @BeforeEach
    void setup() {
        validator = new MovieValidator();
    }

    @Test
    void validateMovieAndMoviePasses() {
        Movie movie = new Movie("Title", "Genre", 2020, 120, 8.5);
        assertDoesNotThrow(() -> validator.validateMovie(movie));
    }

    @Test
    void validateTitleEmptyThrowsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateTitle(""));
    }

    @Test
    void validateGenreEmptyThrowsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateGenre(""));
    }

    @Test
    void validateReleaseYearInvalidThrowsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateReleaseYear(1700));
    }

    @Test
    void validateDurationInvalidThrowsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateDurationMinutes(0));
    }

    @Test
    void validateRatingInvalidThrowsException() {
        assertThrows(MovieValidationException.class,
                () -> validator.validateRating(20));
    }
}