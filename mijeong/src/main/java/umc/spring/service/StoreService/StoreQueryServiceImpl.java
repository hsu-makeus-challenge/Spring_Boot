package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository.StoreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreQueryServiceImpl implements StoreQueryService {
    private final StoreRepository storeRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String storeName, Double storeRating) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(storeName, storeRating);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    // Store 존재 여부 검증
    @Override
    public Boolean existsStoreById(Long storeId) {
        return storeRepository.findById(storeId).isPresent();
    }

    // 가게 반환
    @Override
    public Store validateStore(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.STORE_NOT_FOUND));
    }
}
