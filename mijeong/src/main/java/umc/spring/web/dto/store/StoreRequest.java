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

}
