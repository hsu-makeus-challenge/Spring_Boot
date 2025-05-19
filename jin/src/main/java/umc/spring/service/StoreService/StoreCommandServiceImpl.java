package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.apiPayload.exception.handler.StoreIdHandler;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.store.StoreRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public Store addStore(StoreRequestDTO.AddStoreDto request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));

        Store store = Store.builder()
                .region(region)
                .ownerNum(request.getOwnerNum())
                .storeName(request.getStoreName())
                .category(request.getCategory())
                .openStatus(request.getOpenStatus())
                .address(request.getAddress())
                .score(0.0f)
                .build();

        return storeRepository.save(store);
    }

    @Override
    public void assignRegionToStore(StoreRequestDTO.AssignRegionDto request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreIdHandler(ErrorStatus.STORE_NOT_FOUND));

        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));

        store.assignRegion(region); // 연관관계 메서드로 변경
    }

}
