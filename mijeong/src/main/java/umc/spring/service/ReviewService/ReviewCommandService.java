package umc.spring.service.ReviewService;

import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;

public interface ReviewCommandService {
    // 리뷰 등록
    ReviewResponse.ReviewCreateResultDto saveReview(Long userId, Long storeId, ReviewRequest.ReviewCreateDto requestDto);
}
