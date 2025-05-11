package umc.spring.domain.review.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.domain.review.data.enums.ReviewStatus;

public class ReviewRequestDTO {

    @Getter
    public static class addDto{

        @NotNull @Size(max = 50)
        String content;

        @NotNull @Min(0) @Max(5)
        Float score;

        @NotNull
        ReviewStatus status;

    }

}
