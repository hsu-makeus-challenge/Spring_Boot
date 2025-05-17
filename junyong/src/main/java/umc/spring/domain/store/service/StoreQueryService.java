package umc.spring.domain.store.service;

import org.springframework.data.domain.Page;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.store.data.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    Boolean isExistStore(Long id);

    Page<Review> getReviewList(Long storeId, Integer page);
}
