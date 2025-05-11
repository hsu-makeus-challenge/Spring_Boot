package umc.spring.web.dto.ReviewDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.validation.annotation.ExistStore;

import java.math.BigDecimal;

public class ReviewRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OldCreateReviewDTO {
        @NotNull
        String content; // 리뷰 내용

        @NotNull
        BigDecimal score; //리뷰 점수
    }

    @Getter
    public static class CreateReviewDTO {
        @NotNull
        String content;

        @NotNull
        BigDecimal score;

        @ExistStore
        Long storeId;
    }
}
