package umc.spring.domain.store.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StoreResponseDTO {

    @Builder
    @Getter
    public static class addResultDto {
        Long storeId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class ReviewPreViewListDto {
        List<ReviewPreViewDto> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    public static class ReviewPreViewDto {
        String ownerNickName;
        Float score;
        String body;
        LocalDate createdAt;
    }

}
