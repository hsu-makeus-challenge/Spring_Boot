package umc.spring.service.StoreService;

import umc.spring.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id); // 가게 아이디로 가게 조회
    List<Store> findStoresByNameAndScore(String storeName, Double storeRating); // 이름과 평점으로 가게 조회

    // Store 존재 여부 검증
    Boolean existsStoreById(Long storeId);

    // 가게 반환
    Store validateStore(Long storeId);
}
