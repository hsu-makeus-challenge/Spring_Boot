package umc.spring.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import umc.spring.domain.*;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
    public static ReviewResponse.UserReviewPreViewDto toUserReviewPreViewDto(Review review){
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
                .build();
    }

    // 유저 리뷰 목록 조회 관련 컨버터
    public static ReviewResponse.UserReviewPreViewListDto toUserReviewPreViewListDto(Page<Review> userReviewPage, String ownerNickName) {
        // Review 리스트를 ReviewPreViewDTO 리스트로 변환
        List<ReviewResponse.UserReviewPreViewDto> reviewList = userReviewPage.stream()
                .map(ReviewConverter::toUserReviewPreViewDto)
                .toList();

        return ReviewResponse.UserReviewPreViewListDto.builder()
                .isFirst(userReviewPage.isFirst())
                .isLast(userReviewPage.isLast())
                .totalPage(userReviewPage.getTotalPages())
                .totalElements(userReviewPage.getTotalElements())
                .listSize(reviewList.size())
                .reviewList(reviewList)
                .ownerNickName(ownerNickName)
                .build();
    }

    // 유저 리뷰 목록 슬라이스 조회 관련 컨버터
    public static ReviewResponse.UserReviewPreViewSliceDto toUserReviewPreViewSliceDto(Slice<Review> userReviewSlice, String ownerNickName) {
        // Review 리스트를 ReviewPreViewDTO 리스트로 변환
        List<ReviewResponse.UserReviewPreViewDto> reviewList = userReviewSlice.stream()
                .map(ReviewConverter::toUserReviewPreViewDto)
                .toList();

        return ReviewResponse.UserReviewPreViewSliceDto.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .isFirst(userReviewSlice.isFirst())
                .hasNext(userReviewSlice.isLast())
                .ownerNickName(ownerNickName)
                .build();
    }


    // 유저 리뷰 조회 관련 컨버터 - for
    public static ReviewResponse.UserReviewPreViewDto toUserReviewPreViewDtoWithFor(Review review) {
        List<String> imageUrls = new ArrayList<>();
        for (ReviewImage image : review.getReviewImageList()) {
            log.info("Mapping image URL for review ID {}: {}", review.getId(), image.getReviewImageUrl());
            imageUrls.add(image.getReviewImageUrl());
        }

        return ReviewResponse.UserReviewPreViewDto.builder()
                .reviewId(review.getId())
                .score(review.getReviewRating().floatValue())
                .content(review.getReviewContent())
                .reviewImageList(imageUrls)
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    // 유저 리뷰 목록 조회 관련 컨버터 - for
    public static ReviewResponse.UserReviewPreViewListDto toUserReviewPreViewListDtoWithFor(Page<Review> userReviewPage, String ownerNickName) {
        List<ReviewResponse.UserReviewPreViewDto> reviewList = new ArrayList<>();

        for (Review review : userReviewPage) {
            log.info("Converting review with ID: {}", review.getId());
            ReviewResponse.UserReviewPreViewDto dto = toUserReviewPreViewDtoWithFor(review);
            reviewList.add(dto);
        }

        return ReviewResponse.UserReviewPreViewListDto.builder()
                .isFirst(userReviewPage.isFirst())
                .isLast(userReviewPage.isLast())
                .totalPage(userReviewPage.getTotalPages())
                .totalElements(userReviewPage.getTotalElements())
                .listSize(reviewList.size())
                .reviewList(reviewList)
                .ownerNickName(ownerNickName)
                .build();
    }
}
