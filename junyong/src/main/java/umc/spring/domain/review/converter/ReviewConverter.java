package umc.spring.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.review.web.dto.ReviewRequestDTO;
import umc.spring.domain.review.web.dto.ReviewResponseDTO;
import umc.spring.domain.store.data.Store;

import java.util.ArrayList;
import java.util.List;

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

    public static ReviewResponseDTO.ReviewDto toReviewDto(Review review, Member member) {
        return ReviewResponseDTO.ReviewDto.builder()
                .memberName(member.getName())
                .content(review.getContent())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResponseDTO.ReviewListDto toReviewDtoList(Page<Review> reviewList) {

        List<ReviewResponseDTO.ReviewDto> reviewDtoList = reviewList.stream()
                .map(review -> toReviewDto(review, review.getMember()))
                .toList();

        return ReviewResponseDTO.ReviewListDto.builder()
                .reviewList(reviewDtoList)
                .listSize(reviewList.getSize())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .build();
    }

}
