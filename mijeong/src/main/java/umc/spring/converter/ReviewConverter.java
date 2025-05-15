package umc.spring.converter;

import umc.spring.domain.*;
import umc.spring.domain.mapping.UserPretendFood;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // createDto -> Review Entity
    public static Review toReview(ReviewRequest.ReviewCreateDto request) {
        return Review.builder()
                .reviewContent(request.getReviewContent())
                .reviewRating(request.getReviewRating())
                .build();

    }

    public static ReviewResponse.ReviewCreateResultDto toReviewCreateResultDto(Long reviewId) {
        return ReviewResponse.ReviewCreateResultDto.builder()
                .reviewId(reviewId)
                .build();
    }
}
