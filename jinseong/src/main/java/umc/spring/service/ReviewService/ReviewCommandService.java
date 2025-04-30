package umc.spring.service.ReviewService;

import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;

public interface ReviewCommandService {
    void insertReview(Long userId, Long storeId, ReviewRequestDTO.CreateReviewDTO createReviewDTO);
}
