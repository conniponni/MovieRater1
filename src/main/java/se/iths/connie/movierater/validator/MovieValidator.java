package se.iths.connie.movierater.validator;


import org.springframework.stereotype.Component;
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

        if (title.length() > 100) {
            throw new MovieValidationException("Title cannot exceed 100 characters");
        }
    }

    public void validateGenre(String genre) {

        if (genre == null || genre.trim().isEmpty()) {
            throw new MovieValidationException("Genre cannot be empty");
        }

        if (genre.length() > 50) {
            throw new MovieValidationException("Genre cannot exceed 50 characters");
        }
    }

    public void validateReleaseYear(int year) {

        if (year < 1888) {
            throw new MovieValidationException("Release year cannot be before 1888");
        }

        if (year > 2100) {
            throw new MovieValidationException("Release year invalid");
        }
    }

    public void validateDurationMinutes(int duration) {

        if (duration <= 0) {
            throw new MovieValidationException("Duration must be positive");
        }

        if (duration > 500) {
            throw new MovieValidationException("Duration too large");
        }
    }

    public void validateRating(double rating) {

        if (rating < 0.0 || rating > 10.0) {
            throw new MovieValidationException("Rating must be between 0 and 10");
        }
    }
}