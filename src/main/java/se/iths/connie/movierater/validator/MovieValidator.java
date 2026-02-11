package se.iths.connie.movierater.validator;

import org.springframework.stereotype.Component;
import se.iths.connie.movierater.exception.MovieValidationException;
import se.iths.connie.movierater.model.Movie;

@Component
public class MovieValidator {

    public void validateMovie(Movie movie) {

        validateTitle(movie.getTitle());
        validateGenre(movie.getGenre());
        validateReleaseYear(movie.getReleaseYear());
        validateDurationMinutes(movie.getDurationMinutes());
        validateRating(movie.getRating());
    }

    public void validateTitle(String title) {

        if (title == null || title.trim().isEmpty()) {
            throw new MovieValidationException("Title cannot be empty");
        }
    }

    public void validateGenre(String genre) {

        if (genre == null || genre.trim().isEmpty()) {
            throw new MovieValidationException("Genre cannot be empty");
        }
    }

    public void validateReleaseYear(int year) {

        if (year < 1888 || year > 2100) {
            throw new MovieValidationException("Invalid release year");
        }
    }

    public void validateDurationMinutes(int duration) {

        if (duration <= 0) {
            throw new MovieValidationException("Invalid duration");
        }
    }

    public void validateRating(double rating) {

        if (rating < 0 || rating > 10) {
            throw new MovieValidationException("Invalid rating");
        }
    }
}
