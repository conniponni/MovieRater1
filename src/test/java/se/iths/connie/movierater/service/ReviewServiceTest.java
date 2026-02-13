
package se.iths.connie.movierater.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.connie.movierater.exception.ReviewNotFoundException;
import se.iths.connie.movierater.model.Review;
import se.iths.connie.movierater.repository.ReviewRepository;
import se.iths.connie.movierater.service.ReviewService;
import se.iths.connie.movierater.validator.ReviewValidator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewValidator reviewValidator;

    @InjectMocks
    private ReviewService reviewService;

    private Review testReview;

    @BeforeEach
    void setUp() {
        testReview = new Review();
        testReview.setId(1L);
        testReview.setMovieTitle("The Matrix");
        testReview.setReviewerName("John Doe");
        testReview.setRating(9);
        testReview.setComment("Excellent movie");
        testReview.setCreatedAt(LocalDate.now());
    }

    @Test
    void testGetAllReviews() {
        // Arrange
        Review review2 = new Review();
        review2.setId(2L);
        review2.setMovieTitle("Inception");
        review2.setReviewerName("Jane Smith");
        review2.setRating(8);
        review2.setComment("Mind-bending");
        review2.setCreatedAt(LocalDate.now());

        List<Review> reviews = Arrays.asList(testReview, review2);
        when(reviewRepository.findAll()).thenReturn(reviews);

        // Act
        List<Review> result = reviewService.getAllReviews();

        // Assert
        assertEquals(2, result.size());
        assertEquals("The Matrix", result.get(0).getMovieTitle());
        assertEquals("Inception", result.get(1).getMovieTitle());
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testGetReviewByIdSuccess() {
        // Arrange
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(testReview));

        // Act
        Review result = reviewService.getReviewById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("The Matrix", result.getMovieTitle());
        assertEquals("John Doe", result.getReviewerName());
        assertEquals(9, result.getRating());
        verify(reviewRepository, times(1)).findById(1L);
    }

    @Test
    void testGetReviewByIdThrowsException() {
        // Arrange
        when(reviewRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ReviewNotFoundException.class, () -> {
            reviewService.getReviewById(999L);
        });
        verify(reviewRepository, times(1)).findById(999L);
    }

    @Test
    void testCreateReviewSuccess() {
        // Arrange
        when(reviewRepository.save(any(Review.class))).thenReturn(testReview);
        doNothing().when(reviewValidator).validateMovieTitle(anyString());
        doNothing().when(reviewValidator).validateReviewerName(anyString());
        doNothing().when(reviewValidator).validateRating(anyInt());
        doNothing().when(reviewValidator).validateComment(anyString());

        // Act
        Review result = reviewService.createReview(testReview);

        // Assert
        assertNotNull(result);
        assertEquals("The Matrix", result.getMovieTitle());
        verify(reviewValidator, times(1)).validateMovieTitle("The Matrix");
        verify(reviewValidator, times(1)).validateReviewerName("John Doe");
        verify(reviewValidator, times(1)).validateRating(9);
        verify(reviewValidator, times(1)).validateComment("Excellent movie");
        verify(reviewRepository, times(1)).save(testReview);
    }

    @Test
    void testCreateReviewValidationFails() {
        // Arrange
        doThrow(new IllegalArgumentException("Rating must be between 1 and 10"))
                .when(reviewValidator).validateRating(anyInt());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            reviewService.createReview(testReview);
        });
        verify(reviewRepository, never()).save(any());
    }

    @Test
    void testUpdateReviewSuccess() {
        // Arrange
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(testReview));
        when(reviewRepository.save(any(Review.class))).thenReturn(testReview);
        doNothing().when(reviewValidator).validateMovieTitle(anyString());
        doNothing().when(reviewValidator).validateReviewerName(anyString());
        doNothing().when(reviewValidator).validateRating(anyInt());
        doNothing().when(reviewValidator).validateComment(anyString());

        Review updatedReview = new Review();
        updatedReview.setMovieTitle("The Matrix Reloaded");
        updatedReview.setReviewerName("John Doe");
        updatedReview.setRating(8);
        updatedReview.setComment("Still good");

        // Act
        Review result = reviewService.updateReview(1L, updatedReview);

        // Assert
        assertNotNull(result);
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewValidator, times(1)).validateMovieTitle(anyString());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testUpdateReviewNotFound() {
        // Arrange
        when(reviewRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ReviewNotFoundException.class, () -> {
            reviewService.updateReview(999L, testReview);
        });
        verify(reviewRepository, never()).save(any());
    }

    @Test
    void testDeleteReviewSuccess() {
        // Arrange
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(testReview));
        doNothing().when(reviewRepository).deleteById(1L);

        // Act
        reviewService.deleteReview(1L);

        // Assert
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteReviewNotFound() {
        // Arrange
        when(reviewRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ReviewNotFoundException.class, () -> {
            reviewService.deleteReview(999L);
        });
        verify(reviewRepository, never()).deleteById(any());
    }
}