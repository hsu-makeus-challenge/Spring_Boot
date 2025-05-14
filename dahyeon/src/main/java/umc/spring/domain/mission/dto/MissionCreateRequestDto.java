package umc.spring.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "미션 생성 요청 DTO")
public class MissionCreateRequestDto {
  @Schema(description = "미션 설명", example = "음식 1만원 이상 구매 시 포인트 제공")
  @NotBlank
  private String description;

  @Schema(description = "보상 포인트", example = "500")
  @NotNull
  private Integer rewardPoint;

  @Schema(description = "최소 구매 금액", example = "10000")
  @NotNull
  private Integer minAmount;
}
