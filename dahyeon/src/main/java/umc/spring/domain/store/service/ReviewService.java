package umc.spring.domain.store.service;

import org.springframework.transaction.annotation.Transactional;

import umc.spring.domain.store.dto.ReviewRequestDto;
import umc.spring.domain.store.entity.Review;

public interface ReviewService {
  @Transactional
  Review createReview(Long member, ReviewRequestDto request, Long storeId);
}
