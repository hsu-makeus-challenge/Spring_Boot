package umc.spring.domain.mission.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class addDto{
        @NotBlank
        String content;
        @NotNull
        Integer reward;
        @NotNull
        String deadLine;
    }

}
