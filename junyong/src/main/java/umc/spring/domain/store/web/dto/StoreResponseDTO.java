package umc.spring.domain.store.web.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.global.common.dto.PageDTO;

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
        PageDTO.ListPageDto pageInfo;
    }

    @Builder
    @Getter
    public static class ReviewPreViewDto {
        String ownerNickName;
        Float score;
        String body;
        LocalDate createdAt;
    }

    @Builder
    @Getter
    public static class MissionListDto {
        List<MissionDto> missionList;
        PageDTO.ListPageDto pageInfo;
    }

    @Builder
    @Getter
    public static class MissionDto {
        Long storeId;
        String storeName;
        String storeCategory;
        Long missionId;
        String missionContent;
        Integer missionReward;
        LocalDate deadLine;
    }

}
