package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.web.dto.review.ReviewRequest;

public class ReviewConverter {

    // createDto -> Review Entity
    public static Review toReview(ReviewRequest.ReviewCreateDto requestDTO, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .reviewContent(requestDTO.getReviewContent())
                .reviewRating(requestDTO.getReviewRating())
                .build();

    }
}
