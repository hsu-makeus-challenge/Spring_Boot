package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreIdHandler;
import umc.spring.apiPayload.exception.handler.UserIdHandler;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public boolean existsById(Long storeId) {
        return storeRepository.existsById(storeId);
    }

    @Override
    public Store getStore(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreIdHandler(ErrorStatus.STORE_NOT_FOUND));
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        filteredStores.forEach(store -> System.out.println("Store: " + store));

        return filteredStores;
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page){
        Store store = storeRepository.findById(StoreId)
                .orElseThrow(() -> new StoreIdHandler(ErrorStatus.STORE_NOT_FOUND));

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Review> getMyReviewList(Long StoreId, Long UserId, Integer page){
        Store store = storeRepository.findById(StoreId)
                .orElseThrow(() -> new StoreIdHandler(ErrorStatus.STORE_NOT_FOUND));
        User user = userRepository.findById(UserId)
                .orElseThrow(() -> new UserIdHandler(ErrorStatus.USER_NOT_FOUND));
        Page<Review> MyStoreReviewPage = reviewRepository.findAllByStoreAndUser(store, user, PageRequest.of(page, 10));
        return MyStoreReviewPage;
    }

}

