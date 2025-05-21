package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.util.List;

public interface StoreQueryService {
    boolean existsById(Long storeId);
    Store getStore(Long storeId);
    List<Store> findStoresByNameAndScore(String name, Float score);
    Page<Review> getReviewList(Long StoreId, Integer page);
    Page<Review> getMyReviewList(Long StoreId, Long UserId, Integer page);
}
