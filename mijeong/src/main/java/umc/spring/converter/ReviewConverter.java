package umc.spring.converter;

import umc.spring.domain.*;
import umc.spring.domain.mapping.UserPretendFood;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // createDto -> Review Entity
    public static Review toReview(ReviewRequest.ReviewCreateDto request, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .reviewContent(request.getReviewContent())
                .reviewRating(request.getReviewRating())
                .build();

    }

    // createDto -> Review Image Entity
    public static List<ReviewImage> toReviewImageList(List<String> reviewImageList, Review review) {
        return reviewImageList.stream()
                .map(reviewImage ->
                        ReviewImage.builder()
                                .reviewImageUrl(reviewImage)
                                .build()
                ).collect(Collectors.toList());
    }

    public static ReviewResponse.ReviewCreateResultDto toReviewCreateResultDto(Long reviewId) {
        return ReviewResponse.ReviewCreateResultDto.builder()
                .reviewId(reviewId)
                .build();
    }
}
