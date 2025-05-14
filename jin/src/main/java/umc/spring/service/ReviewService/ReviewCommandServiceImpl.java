package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.service.StoreService.StoreService;
import umc.spring.web.dto.review.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final StoreService storeService;         // 가게 존재 확인 + 가게 조회
    private final ReviewRepository reviewRepository; // 리뷰 저장

    @Override
    public Review writeReview(ReviewRequestDTO.AddDto request, Long storeId) {
        // storeId에 해당하는 Store 조회 (존재하지 않으면 CustomException 발생)
        Store store = storeService.getStore(storeId);

        // Review 엔티티 생성
        Review review = Review.builder()
                .rate(request.getRate())
                .content(request.getContent())
                .build();

        // 연관관계 편의 메서드 사용 → 반드시 Store set 해줘야 JPA에서 정상 동작
        review.setStore(store);

        //  리뷰 저장
        return reviewRepository.save(review);
    }
}

