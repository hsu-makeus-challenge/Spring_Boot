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
    public static class ReviewPreViewListDTO{
        List<ReviewPreViewDTO> reviewList; // 리뷰 목록
        Integer listSize; // 현재 페이지의 리뷰 목록 개수
        Integer totalPage; // 총 페이지
        Long totalElements; // 총 리뷰 개수
        Boolean isFirst; // 페이지 처음 여부
        Boolean isLast; // 페이지 마지막 여부
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "가게의 리뷰 응답 정보")
    public static class ReviewPreViewDTO{
        Long reviewId;
        String ownerNickName;
        Float score;
        String content;
        LocalDate createdAt;
    }
}
