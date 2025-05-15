package umc.spring.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class MissionRequestDTO {

    @Getter
    @Setter
    public static class addMissionDTO {

        @NotNull(message = "보상을 입력해주세요.")
        @Max(value = 100000, message = "보상은 최대 100,000원까지 가능합니다.")
        @Min(value = 500, message = "보상은 최소 500원 이상이어야 합니다.")
        private Long reward;

        @NotBlank(message = "미션 내용을 입력해주세요.")
        private String content;

        @NotNull(message = "마감일을 입력해주세요.")
        @Future(message = "마감일은 현재 시간 이후여야 합니다.")
        private LocalDateTime deadline;

        List<Long> MissionRecord;
    }
}
