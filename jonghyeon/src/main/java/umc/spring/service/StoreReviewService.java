package umc.spring.service;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;
import org.springframework.data.domain.Page;

public interface StoreReviewService {

    public Review writeReview(ReviewRequestDTO.WriteReviewDTO request,Long storeId,Long userId);

    public Page<Review> getMyReviewList(Long storeId, Long userId, Integer page);
}
