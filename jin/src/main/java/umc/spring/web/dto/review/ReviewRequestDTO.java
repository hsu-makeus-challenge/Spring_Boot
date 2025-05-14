package umc.spring.web.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStores;

public class ReviewRequestDTO {

    @Getter
    public static class AddDto {

        @ExistStores // 커스텀 어노테이션
        private Long storeId;

        @NotNull
        @Size(max = 100)
        String content;

        @NotNull
        @Min(0)
        @Max(5)
        Float rate;

    }
}
