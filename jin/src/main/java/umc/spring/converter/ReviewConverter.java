package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewResponseDTO;

public class ReviewConverter {

    public static ReviewResponseDTO.AddResultDTO toReviewResultDTO(Review review) {
        return ReviewResponseDTO.AddResultDTO.builder()
                .reviewId(review.getReviewId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
