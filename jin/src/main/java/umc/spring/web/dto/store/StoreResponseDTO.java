package umc.spring.web.dto.store;

import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.enums.StoreCategory;

import java.time.LocalDateTime;

public class StoreResponseDTO {

    @Getter
    @Builder
    public static class AddStoreResultDto {
        private Long storeId;
        private Long regionId;
        private StoreCategory category;
        private String address;
        private Float score;
        private Boolean openStatus;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class AddRegionResultDto {
        private Long regionId;
        private Long storeId;
    }
}
