package umc.spring.domain.store.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.domain.store.dto.ReviewRequestDto;
import umc.spring.domain.store.entity.Review;

public interface ReviewService {
  @Transactional
  Review createReview(Long member, ReviewRequestDto request, Long storeId);

  Page<Review> getReviewList(Long memberId, Long storeId, Integer page);
}
