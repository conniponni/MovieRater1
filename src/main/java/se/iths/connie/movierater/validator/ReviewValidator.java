package se.iths.connie.movierater.validator;

import org.springframework.stereotype.Component;

@Component
public class ReviewValidator {

    public void validateMovieTitle(String movieTitle) {
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title is required and cannot be empty");
        }
        if (movieTitle.length() > 255) {
            throw new IllegalArgumentException("Movie title cannot exceed 255 characters");
        }
    }

    public void validateReviewerName(String reviewerName) {
        if (reviewerName == null || reviewerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Reviewer name is required and cannot be empty");
        }
        if (reviewerName.length() > 100) {
            throw new IllegalArgumentException("Reviewer name cannot exceed 100 characters");
        }
    }

    public void validateRating(int rating) {
        if (rating < 1 || rating > 10) {
            throw new IllegalArgumentException("Rating must be between 1 and 10");
        }
    }

    public void validateComment(String comment) {
        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment is required and cannot be empty");
        }
        if (comment.length() > 1000) {
            throw new IllegalArgumentException("Comment cannot exceed 1000 characters");
        }
    }
}
