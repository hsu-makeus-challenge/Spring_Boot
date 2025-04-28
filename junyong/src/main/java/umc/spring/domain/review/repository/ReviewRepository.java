package umc.spring.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.review.data.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
