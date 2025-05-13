package umc.study.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {
    @Builder
    @Getter
    public static class ReviewResultDto{
        Long reviewId;
        LocalDateTime createdAt;
    }
}
