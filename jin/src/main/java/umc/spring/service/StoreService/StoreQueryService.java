package umc.spring.service.StoreService;

import umc.spring.domain.Store;

import java.util.List;

public interface StoreQueryService {
    boolean existsById(Long storeId);
    Store getStore(Long storeId);
    List<Store> findStoresByNameAndScore(String name, Float score);
}
