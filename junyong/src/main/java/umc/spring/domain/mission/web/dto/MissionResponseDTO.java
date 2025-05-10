package umc.spring.domain.mission.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Builder
    @Getter
    public static class AddResultDto{
        Long missionId;
        LocalDateTime createdAt;
    }


}
