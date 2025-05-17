package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.StoreMission;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);

    boolean existsById(Long id);

    Page<Review> getReviewList(Long storeId, Integer page);

    Page<StoreMission> getMissionList(Long storeId, Integer page);
}
