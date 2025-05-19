package umc.spring.service.ReviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDTO;

public interface ReviewCommandService {
    Review writeReview(ReviewRequestDTO.AddDto request, Long storeId);
}
