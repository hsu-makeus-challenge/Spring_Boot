package umc.spring.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.mission.entity.mappings.MemberMission;

import java.time.LocalDate;

@Getter
@Builder
public class MissionCompleteResponseDto {
    private Long memberMissionId;
    private Long memberId;
    private Long missionId;
    private String status;
    private LocalDate completedAt;

    public static MissionCompleteResponseDto from(MemberMission memberMission) {
        return MissionCompleteResponseDto.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .completedAt(memberMission.getCompletedAt())
                .build();
    }
}
