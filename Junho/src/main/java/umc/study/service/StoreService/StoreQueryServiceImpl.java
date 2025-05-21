package umc.study.service.StoreService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.StoreConverter;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.domain.mapping.Review;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.validation.annotation.ValidPageNum;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

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

    @Transactional
    public StoreResponseDTO.AddResultDTO createStore(StoreRequestDTO.AddDto request, String region) {
        Store store = StoreConverter.toStore(request,region);
        Store newStore = storeRepository.save(store);
        return new StoreResponseDTO.AddResultDTO(newStore.getId(),newStore.getCreatedAt());
    }

    public boolean isStoreExist(Long id) {
        return storeRepository.existsById(id);
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();
        return reviewRepository.findAllByStore(store, PageRequest.of(page,10));
    }

    @Override
    public Page<Review> getMyReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();
        User user = userRepository.findById(1L).get();
        return reviewRepository.findAllByStoreAndUser(store, user, PageRequest.of(page,10));
    }
}