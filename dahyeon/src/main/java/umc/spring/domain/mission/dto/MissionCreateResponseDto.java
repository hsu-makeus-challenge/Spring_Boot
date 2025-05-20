package umc.spring.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.mission.entity.Mission;

@Getter
@Builder
@Schema(description = "미션 생성 응답 DTO")
public class MissionCreateResponseDto {
  @Schema(description = "미션 ID", example = "1")
  private Long missionId;

  @Schema(description = "미션 설명", example = "음식 1만원 이상 구매 시 포인트 제공")
  private String description;

  @Schema(description = "보상 포인트", example = "500")
  private Integer rewardPoint;

  @Schema(description = "최소 구매 금액", example = "10000")
  private Integer minAmount;

  private String storeName;

  public static MissionCreateResponseDto from(Mission mission) {
    return MissionCreateResponseDto.builder()
        .missionId(mission.getId())
        .description(mission.getDescription())
        .rewardPoint(mission.getRewardPoint())
        .minAmount(mission.getMinAmount())
        .storeName(mission.getStore().getName())
        .build();
  }
}
