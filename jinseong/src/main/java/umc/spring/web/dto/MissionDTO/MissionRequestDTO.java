package umc.spring.web.dto.MissionDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequestDTO {
    @Getter
    public static class CreateStoreMissionDTO {
        @NotNull
        Long missionId;

        @NotNull
        LocalDateTime deadline;
    }
}
