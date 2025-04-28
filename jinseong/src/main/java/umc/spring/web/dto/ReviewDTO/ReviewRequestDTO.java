package umc.spring.web.dto.ReviewDTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

public class ReviewRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewDTO {
        @NotNull
        String content; // 리뷰 내용

        @NotNull
        BigDecimal score; //리뷰 점수
    }
}
