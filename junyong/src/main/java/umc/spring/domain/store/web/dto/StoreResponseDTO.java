package umc.spring.domain.store.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class StoreResponseDTO {

    @Builder
    @Getter
    public static class addResultDto {
        Long storeId;
        LocalDateTime createdAt;
    }

}
