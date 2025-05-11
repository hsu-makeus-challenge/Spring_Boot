package umc.spring.converter;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreDTO.StoreRequestDTO;
import umc.spring.web.dto.StoreDTO.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StoreConverter {
    public static StoreResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store){
        return StoreResponseDTO.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.CreateStoreDto request){
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .storeHoursList(new ArrayList<>())
                .storeFoodCategoryList(new ArrayList<>())
                .build();
    }
}
