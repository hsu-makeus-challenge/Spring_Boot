package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionListDTO {
        List<MissionDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionDTO {
        String storeName;
        String foodCategory;
        LocalDate deadline;
        String body; // 미션 내용 (조건)
        Integer rewardPoint;    // 보상
    }
}
