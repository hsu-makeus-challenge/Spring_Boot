package umc.spring.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class WriteReviewDTO {

        @NotBlank(message = "리뷰 내용을 입력해주세요.")
        @Size(max = 255, message = "리뷰 내용은 최대 255자까지 작성 가능합니다.")
        private String content;

        @NotNull(message = "점수를 입력해주세요.")
        @Max(value = 5, message = "점수는 0.0 ~ 5.0 사이의 값이어야 합니다.")
        @Min(value = 0, message = "점수는 0.0 ~ 5.0 사이의 값이어야 합니다.")
        private Float score;

        @NotNull(message = "이미지 URL 리스트는 비어 있을 수 없습니다.")
        @Size(max = 5, message = "이미지는 최대 5장까지만 등록 가능합니다.")
        private List<String> imageUrlList;
    }
}