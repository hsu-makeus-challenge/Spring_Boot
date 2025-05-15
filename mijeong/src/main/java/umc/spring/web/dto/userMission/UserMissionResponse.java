package umc.spring.web.dto.userMission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UserMissionResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "미션 도전하기 응답 정보")
    public static class UserMissionCreateResultDto {
        @Schema(description = "도전하기에 추가된 유저 미션 아이디", example = "1")
        Long userMissionId;

        @Schema(description = "생성 날짜")
        LocalDateTime createdAt;
    }
}
