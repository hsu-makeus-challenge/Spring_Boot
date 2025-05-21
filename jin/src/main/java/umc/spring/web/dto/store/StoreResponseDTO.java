package umc.spring.web.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.StoreCategory;

import java.time.LocalDateTime;
import java.util.List;

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

    // 리뷰 관련 DTO
    @Builder
    @Getter
    public static class ReviewPreViewListDto {
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
        String ownerNickName;
        Float rate;
        String content;
        LocalDateTime createdAt;
    }

}
