package umc.spring.domain.mission.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionListResponseDto {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Schema(name = "MissionListResponseDto", description = "미션 조회 결과 리스트 DTO (페이징 포함)")
  public static class MissionsPreViewListDto {
    @Schema(description = "미션 미리보기 목록")
    List<MissionCreateResponseDto> missinoList;

    @Schema(description = "현재 페이지의 리뷰 개수", example = "10")
    Integer listSize;

    @Schema(description = "총 페이지 개수", example = "3")
    Integer totalPage;

    @Schema(description = "전체 리뷰 개수", example = "10")
    Long totalElements;

    @Schema(description = "첫 페이지 여부", example = "true")
    Boolean isFirst;

    @Schema(description = "마지막 페이지 여부", example = "fale")
    Boolean isLast;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Schema(name = "MissionListResponseDto", description = "회원이 도전중인 미션 조회 결과 리스트 DTO (페이징 포함)")
  public static class MemberMissionsPreViewListDto {
    @Schema(description = "미션 미리보기 목록")
    List<MissionResponseDto.MemberMissionResponseDto> missinoList;

    @Schema(description = "현재 페이지의 리뷰 개수", example = "10")
    Integer listSize;

    @Schema(description = "총 페이지 개수", example = "3")
    Integer totalPage;

    @Schema(description = "전체 리뷰 개수", example = "10")
    Long totalElements;

    @Schema(description = "첫 페이지 여부", example = "true")
    Boolean isFirst;

    @Schema(description = "마지막 페이지 여부", example = "fale")
    Boolean isLast;
  }
}
