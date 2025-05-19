package umc.spring.web.dto.mission;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.NotAlreadyChallenged;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class AddMissionDto {

        @NotBlank
        private String content;

        @NotNull
        private LocalDate deadline;

        @NotNull @Min(0)
        private Integer point;

    }

    @Getter
    public static class ChallengeDto {

        @NotNull
        @NotAlreadyChallenged
        private Long missionId;
    }
}
