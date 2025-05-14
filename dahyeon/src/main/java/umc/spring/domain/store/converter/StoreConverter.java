package umc.spring.domain.store.converter;

import umc.spring.domain.store.dto.ReviewRequestDto;
import umc.spring.domain.store.dto.ReviewResponseDto;
import umc.spring.domain.store.entity.Review;

public class StoreConverter {
  public static Review toReview(ReviewRequestDto request, Long storeId, Long memberId) {
    return Review.builder()
        .storeId(storeId)
        .memberId(memberId)
        .score(request.getScore())
        .content(request.getContent())
        .build();
  }

  public static ReviewResponseDto.ReviewCreateResult toReviewResponseDto(Review review) {
    return ReviewResponseDto.ReviewCreateResult.builder()
        .reviewId(review.getId())
        .createdAt(review.getCreatedDate())
        .build();
  }
}
