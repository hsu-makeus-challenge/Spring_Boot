package umc.spring.web.dto.store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.enums.StoreCategory;

@Getter
public class StoreRequestDTO {

    @Getter
    public static class AddStoreDto {

        @NotNull
        private Long regionId;

        @NotNull
        private Integer ownerNum;

        @NotBlank
        private String storeName;

        @NotNull
        private StoreCategory category;

        @NotNull
        private Boolean openStatus;

        @NotBlank
        private String address;
    }

    @Getter
    public static class AssignRegionDto {
        @NotNull
        private Long storeId;

        @NotNull
        private Long regionId;
    }

}
