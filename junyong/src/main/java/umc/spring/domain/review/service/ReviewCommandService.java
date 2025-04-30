package umc.spring.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.review.data.enums.ReviewStatus;
import umc.spring.domain.review.repository.ReviewRepository;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public void writeReview(String content, Float score, Long storeId){

        Store store = storeRepository.findById(storeId).orElse(null);

        Review review = Review.builder()
                .content(content)
                .score(score)
                .store(store)
                .member_id(12L)
                .status(ReviewStatus.CREATED)
                .build();

        reviewRepository.save(review);
    }

}
