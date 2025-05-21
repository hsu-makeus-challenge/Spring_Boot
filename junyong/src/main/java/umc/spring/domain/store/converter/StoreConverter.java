package umc.spring.domain.store.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.data.StoreCategory;
import umc.spring.domain.store.web.dto.StoreRequestDTO;
import umc.spring.domain.store.web.dto.StoreResponseDTO;
import umc.spring.global.common.converter.PageConverter;

import java.util.List;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.addStoreDto request) {
        return Store.builder()
                .name(request.getName())
                .category(request.getCategory())
                .address(request.getAddress())
                .score(request.getScore())
                .build();
    }

    public static StoreResponseDTO.addResultDto toResultDto(Store store) {
        return StoreResponseDTO.addResultDto.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDto reviewPreViewDto(Review review) {
        return StoreResponseDTO.ReviewPreViewDto.builder()
                .ownerNickName(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContent())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDto reviewPreViewListDto(Page<Review> reviewList) {

        List<StoreResponseDTO.ReviewPreViewDto> reviewPreViewDtoList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDto).toList();

        return StoreResponseDTO.ReviewPreViewListDto.builder()
                .reviewList(reviewPreViewDtoList)
                .pageInfo(PageConverter.pageToListPageDto(reviewList))
                .build();
    }

    public static StoreResponseDTO.MissionDto toMissionDto(Mission mission, Store store) {
        return StoreResponseDTO.MissionDto.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeCategory(store.getCategory().getName())
                .missionId(mission.getId())
                .missionContent(mission.getContent())
                .missionReward(mission.getReward())
                .deadLine(mission.getDeadline())
                .build();
    }

    public static StoreResponseDTO.MissionListDto toMissionListDto(Page<Mission> missionList) {

        List<StoreResponseDTO.MissionDto> missionDtoList = missionList.stream()
                .map(mission -> StoreConverter.toMissionDto(mission, mission.getStore()))
                .toList();

        return StoreResponseDTO.MissionListDto.builder()
                .missionList(missionDtoList)
                .pageInfo(PageConverter.pageToListPageDto(missionList))
                .build();
    }

}
