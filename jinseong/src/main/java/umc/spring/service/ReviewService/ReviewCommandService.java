package umc.spring.service.ReviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;

public interface ReviewCommandService {
    void insertReview(Long userId, Long storeId, ReviewRequestDTO.OldCreateReviewDTO oldCreateReviewDTO);

    Review createReview(Long userId, ReviewRequestDTO.CreateReviewDTO request);
}
