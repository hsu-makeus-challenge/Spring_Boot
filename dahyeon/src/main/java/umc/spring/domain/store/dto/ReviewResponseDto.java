package umc.spring.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDto {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 생성 응답 DTO")
    public static class ReviewCreateResult{
      @Schema(description = "리뷰 ID", example = "1")
      Long reviewId;

      @Schema(description = "리뷰 생성 시각 (ISO 8601 형식)", example = "2025-05-12T13:45:00")
      LocalDateTime createdAt;

    }


}
