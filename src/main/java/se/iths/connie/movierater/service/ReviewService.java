package se.iths.connie.movierater.service;

import org.springframework.stereotype.Service;
import se.iths.connie.movierater.exception.ReviewNotFoundException;
import se.iths.connie.movierater.model.Review;
import se.iths.connie.movierater.repository.ReviewRepository;
import se.iths.connie.movierater.validator.ReviewValidator;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewValidator reviewValidator;

    public ReviewService(ReviewRepository reviewRepository, ReviewValidator reviewValidator) {
        this.reviewRepository = reviewRepository;
        this.reviewValidator = reviewValidator;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    public Review createReview(Review review) {
        reviewValidator.validateMovieTitle(review.getMovieTitle());
        reviewValidator.validateReviewerName(review.getReviewerName());
        reviewValidator.validateRating(review.getRating());
        reviewValidator.validateComment(review.getComment());
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review review) {
        reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        
        reviewValidator.validateMovieTitle(review.getMovieTitle());
        reviewValidator.validateReviewerName(review.getReviewerName());
        reviewValidator.validateRating(review.getRating());
        reviewValidator.validateComment(review.getComment());
        
        review.setId(id);
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        reviewRepository.deleteById(id);
    }
}
