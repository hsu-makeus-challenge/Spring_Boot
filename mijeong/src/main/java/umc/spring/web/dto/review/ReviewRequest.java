package umc.spring.web.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public class ReviewRequest {

    @Getter
    @Schema(description = "리뷰 등록 요청 정보")
    public static class ReviewCreateDto {

        @NotNull @DecimalMin(value = "0.5") @DecimalMax(value = "5.0")
        @Schema(description = "평점", example = "3.5")
        private Float reviewRating;

        @NotNull @Size(min = 1, max = 500)
        @Schema(description = "리뷰 내용", example = "너무 맛있어서 눈물이 나네요")
        private String reviewContent;

        @Schema(description = "리뷰 이미지 URL 리스트")
        private List<String> reviewImages;
    }
}
