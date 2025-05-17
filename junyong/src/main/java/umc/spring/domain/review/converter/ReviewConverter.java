package umc.spring.domain.review.converter;

import umc.spring.domain.member.data.Member;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.review.web.dto.ReviewRequestDTO;
import umc.spring.domain.review.web.dto.ReviewResponseDTO;
import umc.spring.domain.store.data.Store;

import java.util.ArrayList;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.addDto request, Member member, Store store) {
        return Review.builder()
                .content(request.getContent())
                .score(request.getScore())
                .status(request.getStatus())
                .member(member)
                .store(store)
                .replyList(new ArrayList<>())
                .build();
    }

    public static ReviewResponseDTO.addResultDto toResultDto(Review review) {
        return ReviewResponseDTO.addResultDto.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

}
