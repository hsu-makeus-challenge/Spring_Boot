package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public void writeReview(Long storeId, Float rate, String content) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 존재하지 않습니다."));

        Review review = Review.builder()
                .store(store)
                .rate(rate)
                .content(content)
                .build();

//        reviewRepository.writeReview(review); // 직접 EntityManager로 저장
        reviewRepository.save(review);
    }
}

