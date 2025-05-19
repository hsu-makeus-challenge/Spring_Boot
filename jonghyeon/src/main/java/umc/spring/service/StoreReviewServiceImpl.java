package umc.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UserRepository;

import umc.spring.web.dto.ReviewRequestDTO;

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
                .orElseThrow();

        User user = userRepository.findById(userId)
                .orElseThrow();

        Review review = ReviewConverter.toReview(request, store, user);

        return reviewRepository.save(review);
    }
}
