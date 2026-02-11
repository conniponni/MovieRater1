package se.iths.connie.movierater.validator;

import org.springframework.stereotype.Component;
import se.iths.connie.movierater.exception.ValidationException;
import se.iths.connie.movierater.model.Director;

import java.time.LocalDate;

@Component
public class DirectorValidator {

    public void validate(Director director) {

        validateName(director.getName());
        validateBirthYear(director.getBirthYear());
        validateNationality(director.getNationality());
        validateNumberOfMovies(director.getNumberOfMoviesDirected());
        validateActive(director.isActive());
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("The name can't be empty");
        }
    }

    private void validateBirthYear(int birthYear) {
        int currentYear = LocalDate.now().getYear();

        if (birthYear > currentYear || birthYear < 1800) {
            throw new ValidationException("Invalid birth year");
        }
    }

    private void validateNationality(String nationality) {
        if (nationality == null || nationality.trim().isEmpty()) {
            throw new ValidationException("You forgot 'nationality'");
        }
    }

    private void validateNumberOfMovies(int numberOfMoviesDirected) {
        if (numberOfMoviesDirected < 0) {
            throw new ValidationException("Number of movies can't be less than 0");
        }
    }

    private void validateActive(boolean active) {
    }
}
