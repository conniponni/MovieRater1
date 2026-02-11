package se.iths.connie.movierater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.connie.movierater.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
