package umc.spring.service;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface StoreReviewService {

    public Review writeReview(ReviewRequestDTO.WriteReviewDTO request,Long storeId);
}
