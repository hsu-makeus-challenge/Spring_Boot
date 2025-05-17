package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreMissionRepository.StoreMissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final StoreMissionRepository storeMissionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    @Override
    public boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));

        return storePage;
    }

    @Override
    public Page<StoreMission> getMissionList(Long storeId, Integer page) {

        // 1) 가게 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));


        Pageable pageable = PageRequest.of(page, 10);


        // 2) 리뷰 ID 조회
        Page<Long> storeMissionIdPage = storeMissionRepository.findStoreMissionIdsByStore(store, pageable);

        List<Long> storeMissionIds = storeMissionIdPage.getContent();

        if (storeMissionIds.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }


        // 3) 가게별 미션 + 미션 조회
        List<StoreMission> storeMissions = storeMissionRepository.findStoreMissionsByIdWithMissions(storeMissionIds);

        return new PageImpl<>(storeMissions, pageable, storeMissionIdPage.getTotalElements());
    }
}
