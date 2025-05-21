package umc.study.converter;

import umc.study.domain.mapping.Review;
import umc.study.domain.*;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static Review toReview(ReviewRequestDTO.AddDto request,User user,Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .score(request.getScore())
                .content(request.getContent())
                .build();
    }

    public static ReviewResponseDTO.writeResultDTO toReviewResponseDTO(Review review){
        return ReviewResponseDTO.writeResultDTO.builder()
                .id(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
