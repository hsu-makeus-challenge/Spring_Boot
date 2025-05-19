package umc.study.converter;

import umc.study.domain.Review;
import umc.study.domain.Users;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewResultDto toDto(Review review) {
        return ReviewResponseDTO.ReviewResultDto.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(Users user, ReviewRequestDTO.ReviewDto request) {
        return Review.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

    }

}
