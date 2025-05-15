package umc.spring.domain.store.converter;

import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.web.dto.StoreRequestDTO;
import umc.spring.domain.store.web.dto.StoreResponseDTO;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.addStoreDto request) {
        return Store.builder()
                .name(request.getName())
                .categoryId(request.getCategoryId())
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

}
