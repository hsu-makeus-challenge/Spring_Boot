package umc.spring.web.dto.mission;

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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddResultDTO{
        Long missionId;
        LocalDateTime createdAt;
        Boolean isReviewed;
    }

    @Builder
    @Getter
    public static class ChallengeResultDTO{
        Long missionId;
        Long userId;
        LocalDateTime createdAt;
    }

    // 가게 미션
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDto {
        List<MissionPreViewDto> missionList;
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
    public static class MissionPreViewDto {
        String content;
        LocalDate deadline;
        Integer point;
        Boolean isReviewed;
    }
}
