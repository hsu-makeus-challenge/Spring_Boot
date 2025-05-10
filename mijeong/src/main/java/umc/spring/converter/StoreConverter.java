package umc.spring.converter;

import umc.spring.domain.*;
import umc.spring.web.dto.store.StoreRequest;
import umc.spring.web.dto.store.StoreResponse;

public class StoreConverter {

    // createDto -> Store Entity
    public static Store toStore(StoreRequest.StoreCreateDto request) {
        return Store.builder()
                .storeName(request.getStoreName())
                .storeAddress(request.getStoreAddress())
                .build();
    }

    public static StoreResponse.StoreCreateResultDto toStoreCreateResultDto(Long storeId) {
        return StoreResponse.StoreCreateResultDto.builder()
                .storeId(storeId)
                .build();
    }
}
