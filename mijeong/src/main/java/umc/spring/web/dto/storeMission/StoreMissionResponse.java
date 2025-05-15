package umc.spring.web.dto.storeMission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class StoreMissionResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "가게에 미션 추가 응답 정보")
    public static class StoreMissionCreateResultDto {
        @Schema(description = "추가된 가게 미션 아이디", example = "1")
        Long storeMissionId;

        @Schema(description = "생성 날짜")
        LocalDateTime createdAt;
    }
}
