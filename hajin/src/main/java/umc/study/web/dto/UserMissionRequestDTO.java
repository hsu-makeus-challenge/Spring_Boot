package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.enums.MissionStatus;

import java.time.LocalDateTime;

public class UserMissionRequestDTO {

    @Getter
    @Setter
    public static class UserMissionDto{
        private MissionStatus missionStatus;
        private LocalDateTime deadLine;
    }

}
