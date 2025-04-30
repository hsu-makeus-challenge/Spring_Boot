package umc.spring.domain.mission.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.entity.enums.MissionStatus;
import umc.spring.domain.mission.entity.mappings.MemberMission;

public class MissionResponseDto {

  @Getter
  @Builder
  public static class MemberMissionResponseDto {
    private Long missionId;
    private String description;
    private Integer rewardPoint;
    private Integer minAmount;
    private MissionStatus status;
    private LocalDate completedAt;
    private String storeName;

    public static MemberMissionResponseDto from(MemberMission memberMission) {
      Mission mission = memberMission.getMission();
      return MemberMissionResponseDto.builder()
          .missionId(mission.getId())
          .description(mission.getDescription())
          .rewardPoint(mission.getRewardPoint())
          .minAmount(mission.getMinAmount())
          .status(memberMission.getStatus())
          .completedAt(memberMission.getCompletedAt())
          .storeName(mission.getStore().getName())
          .build();
    }
  }
}
