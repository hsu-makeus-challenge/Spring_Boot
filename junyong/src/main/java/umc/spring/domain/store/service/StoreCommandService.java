package umc.spring.domain.store.service;

import umc.spring.domain.region.data.Region;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store addStore(StoreRequestDTO.addStoreDto request, Region region);
}
