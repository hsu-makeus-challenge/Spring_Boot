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
    public Review writeReview(ReviewRequestDTO.WriteReviewDTO request, Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("해당 가게를 찾을 수 없습니다"));

        User user = userRepository.findById(1L) // ✅ 실제 저장된 유저 ID
                .orElseThrow(() -> new RuntimeException("해당 유저를 찾을 수 없습니다"));

        Review review = ReviewConverter.toReview(request, store, user);

        return reviewRepository.save(review);
    }
}
