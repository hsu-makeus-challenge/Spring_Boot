package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;

public class ReviewConverter {
    public static Review toReview(User user, Store store, ReviewRequestDTO.CreateReviewDTO createReviewDTO) {
        return Review.builder()
                .content(createReviewDTO.getContent())
                .score(createReviewDTO.getScore())
                .user(user)
                .store(store)
                .build()
                ;
    }
}
