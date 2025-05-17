package umc.spring.web.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class StoreRequest {

    @Getter
    @Schema(description = "가게 등록 요청 정보")
    public static class StoreCreateDto {

        @NotNull @Size(min = 1, max = 20)
        @Schema(description = "가게 이름", example = "마포 맛집")
        private String storeName;

        @NotNull @Size(min = 1, max = 50)
        @Schema(description = "가게 주소", example = "서울시 마포구 연남동")
        private String storeAddress;

        @ExistCategories
        @NotEmpty
        @Schema(description = "가게 음식 카테고리 아이디", example = "[1, 2]")
        List<Long> storeFoodCategories;

        @Schema(description = "가게 이미지 URL 리스트")
        private List<String> storeImages;
    }

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
