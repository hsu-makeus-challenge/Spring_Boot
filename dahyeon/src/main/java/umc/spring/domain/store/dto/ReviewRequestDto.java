package umc.spring.domain.store.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReviewRequestDto {

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CreateReviewDto {
    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;

    @NotBlank(message = "리뷰 내용은 필수입니다.")
    @Size(min = 1, max = 1000, message = "리뷰 내용은 1자 이상 1000자 이하여야 합니다.")
    private String content;

    @NotNull(message = "별점은 필수입니다.")
    @Min(value = 0, message = "별점은 0점 이상이어야 합니다.")
    @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
    private Float score;
  }
}
