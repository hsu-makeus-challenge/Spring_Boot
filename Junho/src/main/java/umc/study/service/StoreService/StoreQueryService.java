package umc.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.study.domain.Store;
import umc.study.domain.mapping.Review;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    Page<Review> getReviewList(Long StoreId, Integer page);
    Page<Review> getMyReviewList(Long StoreId,Integer page);
}