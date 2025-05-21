package umc.spring.web.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


public class ReviewResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 등록 응답 정보")
    public static class ReviewCreateResultDto {
        @Schema(description = "등록된 리뷰 아이디", example = "1")
        Long reviewId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "가게의 리뷰 목록 응답 정보")
    public static class StoreReviewPreViewListDto {
        @Schema(description = "가게의 리뷰 목록")
        List<StoreReviewPreViewDto> reviewList;

        @Schema(description = "현재 페이지의 리뷰 목록 개수", example = "10")
        Integer listSize;

        @Schema(description = "총 페이지 수", example = "8")
        Integer totalPage;

        @Schema(description = "총 리뷰 개수", example = "80")
        Long totalElements;

        @Schema(description = "페이지 처음 여부", example = "true")
        Boolean isFirst;

        @Schema(description = "페이지 마지막 여부", example = "false")
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "가게의 리뷰 응답 정보")
    public static class StoreReviewPreViewDto {
        @Schema(description = "리뷰 아이디", example = "1")
        Long reviewId;

        @Schema(description = "리뷰 작성자 닉네임", example = "1")
        String ownerNickName;

        @Schema(description = "리뷰 평점", example = "4.5")
        Float score;

        @Schema(description = "리뷰 내용", example = "너무 맛있어요")
        String content;

        @Schema(description = "리뷰 등록 날짜", example = "2025-05-16")
        LocalDate createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "유저 리뷰 목록 응답 정보")
    public static class UserReviewPreViewListDto {
        @Schema(description = "유저의 리뷰 목록")
        List<UserReviewPreViewDto> reviewList;

        @Schema(description = "현재 페이지의 리뷰 목록 개수", example = "10")
        Integer listSize;

        @Schema(description = "총 페이지 수", example = "8")
        Integer totalPage;

        @Schema(description = "총 리뷰 개수", example = "80")
        Long totalElements;

        @Schema(description = "페이지 처음 여부", example = "true")
        Boolean isFirst;

        @Schema(description = "페이지 마지막 여부", example = "false")
        Boolean isLast;

        @Schema(description = "리뷰 작성자 닉네임", example = "1")
        String ownerNickName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "유저 리뷰 목록 슬라이스 응답 정보")
    public static class UserReviewPreViewSliceDto {

        @Schema(description = "유저의 리뷰 목록")
        private List<UserReviewPreViewDto> reviewList;

        @Schema(description = "현재 페이지의 리뷰 목록 개수", example = "10")
        private Integer listSize;

        @Schema(description = "페이지 처음 여부", example = "true")
        private Boolean isFirst;

        @Schema(description = "다음 페이지 존재 여부", example = "true")
        private Boolean hasNext;

        @Schema(description = "리뷰 작성자 닉네임", example = "1")
        private String ownerNickName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "사용자 리뷰 응답 정보")
    public static class UserReviewPreViewDto{
        @Schema(description = "리뷰 아이디", example = "1")
        Long reviewId;

        @Schema(description = "리뷰 평점", example = "4.5")
        Float score;

        @Schema(description = "리뷰 내용", example = "너무 맛있어요")
        String content;

        @Schema(description = "리뷰 등록 날짜", example = "2025-05-16")
        LocalDate createdAt;

        @Schema(description = "리뷰 이미지", example = "2025-05-16")
        List<String> reviewImageList;
    }
}
