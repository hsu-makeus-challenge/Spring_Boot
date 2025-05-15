package umc.spring.web.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
}
