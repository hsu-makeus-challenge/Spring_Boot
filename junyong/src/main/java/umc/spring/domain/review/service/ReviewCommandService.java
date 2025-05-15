package umc.spring.domain.review.service;

import umc.spring.domain.review.data.Review;
import umc.spring.domain.review.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    Review createReview(ReviewRequestDTO.addDto request, Long storeId);
}
