package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.NeighborhoodHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.converter.StoreFoodConverter;
import umc.spring.converter.StoreHoursConverter;
import umc.spring.domain.*;
import umc.spring.domain.mapping.StoreFoodCategory;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.ReviewDTO.ReviewRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public void insertReview(Long userId, Long storeId, ReviewRequestDTO.OldCreateReviewDTO oldCreateReviewDTO) {
        User user = userRepository.findById(userId).orElseThrow();
        Store store = storeRepository.findById(storeId).orElseThrow();

        Review review = ReviewConverter.toReview(user, store, oldCreateReviewDTO);

        reviewRepository.save(review);
    }

    @Override
    public Review createReview(Long userId, ReviewRequestDTO.CreateReviewDTO request) {
        Review newReview = ReviewConverter.toReview(request);

        // 사용자 연관관계 설정
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        newReview.setUser(user);

        // 가게 연관관계 설정
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        newReview.setStore(store);

        return reviewRepository.save(newReview);
    }
}
