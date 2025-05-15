package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.spring.web.dto.ReviewDTO.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static Review toReview(User user, Store store, ReviewRequestDTO.OldCreateReviewDTO oldCreateReviewDTO) {
        return Review.builder()
                .content(oldCreateReviewDTO.getContent())
                .score(oldCreateReviewDTO.getScore())
                .user(user)
                .store(store)
                .build()
                ;
    }

    public static ReviewResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.CreateReviewDTO request) {
        return Review.builder()
                .content(request.getContent())
                .score(request.getScore())
                .build()
                ;
    }
}
