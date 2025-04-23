package umc.spring.service.ReviewService;

import umc.spring.web.dto.review.ReviewRequest;

public interface ReviewCommandService {
    // 리뷰 등록
    void saveReview(ReviewRequest.ReviewCreateDto requestDTO);
}
