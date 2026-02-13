
package se.iths.connie.movierater.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ReviewValidatorTest {

    private ReviewValidator reviewValidator;

    @BeforeEach
    void setUp() {
        reviewValidator = new ReviewValidator();
    }

    // Movie Title Tests
    @Test
    void testValidMovieTitle() {
        assertDoesNotThrow(() -> {
            reviewValidator.validateMovieTitle("The Matrix");
        });
    }

    @Test
    void testEmptyMovieTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateMovieTitle("");
        });
    }

    @Test
    void testNullMovieTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateMovieTitle(null);
        });
    }

    @Test
    void testMovieTitleTooLong() {
        String longTitle = "a".repeat(256);
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateMovieTitle(longTitle);
        });
    }

    @Test
    void testMovieTitleMaxLength() {
        String maxTitle = "a".repeat(255);
        assertDoesNotThrow(() -> {
            reviewValidator.validateMovieTitle(maxTitle);
        });
    }

    // Reviewer Name Tests
    @Test
    void testValidReviewerName() {
        assertDoesNotThrow(() -> {
            reviewValidator.validateReviewerName("John Doe");
        });
    }

    @Test
    void testEmptyReviewerName() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateReviewerName("");
        });
    }

    @Test
    void testNullReviewerName() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateReviewerName(null);
        });
    }

    @Test
    void testReviewerNameTooLong() {
        String longName = "a".repeat(101);
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateReviewerName(longName);
        });
    }

    @Test
    void testReviewerNameMaxLength() {
        String maxName = "a".repeat(100);
        assertDoesNotThrow(() -> {
            reviewValidator.validateReviewerName(maxName);
        });
    }

    // Rating Tests
    @Test
    void testValidRatingMin() {
        assertDoesNotThrow(() -> {
            reviewValidator.validateRating(1);
        });
    }

    @Test
    void testValidRatingMax() {
        assertDoesNotThrow(() -> {
            reviewValidator.validateRating(10);
        });
    }

    @Test
    void testValidRatingMiddle() {
        assertDoesNotThrow(() -> {
            reviewValidator.validateRating(5);
        });
    }

    @Test
    void testRatingTooLow() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateRating(0);
        });
    }

    @Test
    void testRatingTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateRating(11);
        });
    }

    @Test
    void testRatingNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateRating(-5);
        });
    }

    // Comment Tests
    @Test
    void testValidComment() {
        assertDoesNotThrow(() -> {
            reviewValidator.validateComment("This is a great movie!");
        });
    }

    @Test
    void testEmptyComment() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateComment("");
        });
    }

    @Test
    void testNullComment() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateComment(null);
        });
    }

    @Test
    void testCommentTooLong() {
        String longComment = "a".repeat(1001);
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateComment(longComment);
        });
    }

    @Test
    void testCommentMaxLength() {
        String maxComment = "a".repeat(1000);
        assertDoesNotThrow(() -> {
            reviewValidator.validateComment(maxComment);
        });
    }

    @Test
    void testCommentWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateComment("   ");
        });
    }
}