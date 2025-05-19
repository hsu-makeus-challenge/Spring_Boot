package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreResponseDTO;

public class StoreConverter {

    public static StoreResponseDTO.AddStoreResultDto toAddStoreResultDto(Store store) {
        return StoreResponseDTO.AddStoreResultDto.builder()
                .storeId(store.getStoreId())
                .regionId(store.getRegion().getRegionId())
                .category(store.getCategory())
                .address(store.getAddress())
                .score(store.getScore())
                .openStatus(store.getOpenStatus())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static StoreResponseDTO.AddRegionResultDto addRegionResultDto(Store store) {
        return StoreResponseDTO.AddRegionResultDto.builder()
                .storeId(store.getStoreId())
                .regionId(store.getRegion().getRegionId())
                .build();
    }
}
