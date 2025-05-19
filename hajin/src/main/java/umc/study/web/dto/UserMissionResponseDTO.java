package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.MissionStatus;

import java.time.LocalDateTime;


public class UserMissionResponseDTO {

    @Builder
    @Getter
    @Setter
    public static class UserMissionResultDto {
        private Integer userId;
        private Long missionId;
        private MissionStatus missionStatus;
        private LocalDateTime deadLine;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
