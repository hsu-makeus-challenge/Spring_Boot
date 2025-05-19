package umc.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.exception.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UserRepository;

import umc.spring.web.dto.ReviewRequestDTO;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StoreReviewServiceImpl implements StoreReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public Review writeReview(ReviewRequestDTO.WriteReviewDTO request, Long storeId,Long userId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, store, user);

        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Page<Review> getMyReviewList(Long storeId, Long userId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        return reviewRepository.findAllByStoreAndUser(store, user, PageRequest.of(page, 10));
    }
}
