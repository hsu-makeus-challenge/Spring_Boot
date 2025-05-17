package umc.spring.web.dto.store;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "가게 등록 응답 정보")
    public static class StoreCreateResultDto {
        @Schema(description = "등록된 가게 아이디", example = "1")
        Long storeId;
    }
}
