package umc.spring.domain.store.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDto {
  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Schema(name = "ReviewCreateResultDto", description = "리뷰 생성 응답 Dto")
  public static class ReviewCreateResultDto {
    @Schema(description = "리뷰 ID", example = "1")
    Long reviewId;

    @Schema(
        description = "리뷰 생성 시각 (ISO 8601 형식)",
        example = "2025-05-12T13:45:00",
        type = "string",
        format = "date-time")
    LocalDateTime createdAt;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  @Schema(name = "ReviewPreViewListDto", description = "리뷰 조회 결과 리스트 DTO (페이징 포함)")
  public static class ReviewPreViewListDto {
    @Schema(description = "리뷰 미리보기 목록")
    List<ReviewPreViewDto> reviewList;

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
  @Schema(name = "ReviewPreViewDto", description = "리뷰 조회 Dto")
  public static class ReviewPreViewDto {
    @Schema(description = "리뷰 작성자의 닉네임", example = "현다")
    String nickname;

    @Schema(description = "리뷰 내용", example = "와 맛있다.....")
    String content;

    @Schema(description = "리뷰 평점", example = "5.0")
    Float score;

    @Schema(description = "가게 이름", example = "반야마라탕")
    String storeName;

    @Schema(
        description = "리뷰 생성 시각 (ISO 8601 형식)",
        example = "2025-05-12T13:45:00",
        type = "string",
        format = "date-time")
    LocalDateTime createdDate;
  }
}
