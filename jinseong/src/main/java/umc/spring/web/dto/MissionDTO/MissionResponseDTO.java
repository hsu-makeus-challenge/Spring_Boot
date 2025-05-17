package umc.spring.web.dto.MissionDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.UserMissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateStoreMissionResultDTO{
        Long storeMissionId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateUserMissionResultDTO{
        Long userMissionId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompleteUserMissionResultDTO{
        Long userMissionId;
        UserMissionStatus status;
        LocalDate updatedAt;
    }
}
