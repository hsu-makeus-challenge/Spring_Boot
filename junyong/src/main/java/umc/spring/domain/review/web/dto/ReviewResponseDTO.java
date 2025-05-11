package umc.spring.domain.review.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Builder
    @Getter
    public static class addResultDto{
        Long reviewId;
        LocalDateTime createdAt;
    }

}
