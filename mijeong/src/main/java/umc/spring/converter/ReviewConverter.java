package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.*;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;

import java.util.List;

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

    // 가게 리뷰 조회 관련 컨버터
    public static ReviewResponse.StoreReviewPreViewDto toStoreReviewPreViewDto(Review review){
        return ReviewResponse.StoreReviewPreViewDto.builder()
                .reviewId(review.getId())
                .ownerNickName(review.getUser().getNickName())
                .score(review.getReviewRating().floatValue())
                .content(review.getReviewContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    // 가게 리뷰 목록 조회 관련 컨버터
    // Page를 파라미터로 받음
    public static ReviewResponse.StoreReviewPreViewListDto toStoreReviewPreViewListDto(Page<Review> storeReviewPage) {
        // Review 리스트를 ReviewPreViewDTO 리스트로 변환
        List<ReviewResponse.StoreReviewPreViewDto> reviewList = storeReviewPage.stream()
                .map(ReviewConverter::toStoreReviewPreViewDto) // Review를 StoreReviewPreViewDto로 변환
                .toList();

        return ReviewResponse.StoreReviewPreViewListDto.builder()
                .isFirst(storeReviewPage.isFirst())
                .isLast(storeReviewPage.isLast())
                .totalPage(storeReviewPage.getTotalPages())
                .totalElements(storeReviewPage.getTotalElements())
                .listSize(reviewList.size())
                .reviewList(reviewList)
                .build();
    }

    // 유저 리뷰 조회 관련 컨버터
    public static ReviewResponse.UserReviewPreViewDto toUserReviewPreViewDto(Review review, String ownerNickName){
        return ReviewResponse.UserReviewPreViewDto.builder()
                .reviewId(review.getId())
                .score(review.getReviewRating().floatValue())
                .content(review.getReviewContent())
                .reviewImageList(
                        review.getReviewImageList().stream()
                                .map(ReviewImage::getReviewImageUrl)
                                .toList()
                )
                .createdAt(review.getCreatedAt().toLocalDate())
                .ownerNickName(ownerNickName)
                .build();
    }

    // 유저 리뷰 목록 조회 관련 컨버터
    public static ReviewResponse.UserReviewPreViewListDto toUserReviewPreViewListDto(Page<Review> userReviewPage, String ownerNickName) {
        // Review 리스트를 ReviewPreViewDTO 리스트로 변환
        List<ReviewResponse.UserReviewPreViewDto> reviewList = userReviewPage.stream()
                .map(review -> toUserReviewPreViewDto(review, ownerNickName))
                .toList();

        return ReviewResponse.UserReviewPreViewListDto.builder()
                .isFirst(userReviewPage.isFirst())
                .isLast(userReviewPage.isLast())
                .totalPage(userReviewPage.getTotalPages())
                .totalElements(userReviewPage.getTotalElements())
                .listSize(reviewList.size())
                .reviewList(reviewList)
                .build();
    }
}
