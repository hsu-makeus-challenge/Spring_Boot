package umc.spring.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.region.data.Region;
import umc.spring.domain.store.converter.StoreConverter;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.repository.StoreRepository;
import umc.spring.domain.store.web.dto.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;

    @Override
    public Store addStore(StoreRequestDTO.addStoreDto request, Region region) {
        Store store = StoreConverter.toStore(request);
        store.setRegion(region);
        return storeRepository.save(store);
    }
}
