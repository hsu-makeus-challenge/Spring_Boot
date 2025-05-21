package umc.spring.domain.member.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.global.common.dto.PageDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageDTO {
        Long id;
        String name;
        String email;
        String phone;
        Integer point;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDto{
        Long memberId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class MissionListDto {
        List<MissionDto> missionList;
        PageDTO.ListPageDto pageInfo;
    }

    @Builder
    @Getter
    public static class MissionDto {
        Long missionId;
        String storeName;
        String missionContent;
        Integer missionReward;
        LocalDate deadLine;
        String status;
    }

    @Builder
    @Getter
    public static class CompleteDto{
        Long memberMissionId;
        Long missionId;
        LocalDateTime completedAt;
    }


}
