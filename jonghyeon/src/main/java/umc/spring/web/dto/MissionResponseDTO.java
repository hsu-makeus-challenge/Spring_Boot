package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addMissionResultDTO {
        Long missionId;
        LocalDateTime createdAt;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDTO {
        Long missionId;
        String title;
        String content;
        Long reward;
        LocalDateTime deadline;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class storeMissionListDTO {
        List<MissionDTO> storeMissionList;
        int listSize;
        int totalPage;
        long totalElements;
        boolean isFirst;
        boolean isLast;
    }
}
