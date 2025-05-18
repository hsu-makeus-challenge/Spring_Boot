package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionResponseDTO {
    @Builder
    @Getter
    public static class MissionResultDTO {
        Long missionId;
        Long storeId;
        String title;
        String body;
        Integer rewardPoint;
//        LocalDateTime start_date;
//        LocalDateTime end_date;
        LocalDateTime created_at;
        LocalDateTime updated_at;
    }
}
