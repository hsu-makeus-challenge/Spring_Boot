package umc.spring.converter;

import org.springframework.data.domain.Page;
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

    // 리뷰 조회 관련 컨버터
    public static ReviewResponse.ReviewPreViewDTO toReviewPreViewDTO(Review review){
        return ReviewResponse.ReviewPreViewDTO.builder()
                .reviewId(review.getId())
                .ownerNickName(review.getUser().getNickName())
                .score(review.getReviewRating().floatValue())
                .content(review.getReviewContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    // 리뷰 목록 조회 관련 컨버터
    // Page를 파라미터로 받음
    public static ReviewResponse.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> storeReviewPage){
        // Review 리스트를 ReviewPreViewDTO 리스트로 변환
        List<ReviewResponse.ReviewPreViewDTO> reviewList = storeReviewPage.stream()
                .map(ReviewConverter::toReviewPreViewDTO) // Review를 ReviewPreViewDTO로 변환
                .toList();

        return ReviewResponse.ReviewPreViewListDTO.builder()
                .isFirst(storeReviewPage.isFirst())
                .isLast(storeReviewPage.isLast())
                .totalPage(storeReviewPage.getTotalPages())
                .totalElements(storeReviewPage.getTotalElements())
                .listSize(reviewList.size())
                .reviewList(reviewList)
                .build();
    }
}
