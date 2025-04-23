package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UserRepository.umc.spring.repository.UserRepository;
import umc.spring.web.dto.review.ReviewRequest;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public void saveReview(ReviewRequest.ReviewCreateDto request) {
        User user = userRepository.findById(request.getUsrId())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게가 존재하지 않습니다."));

        // Review 객체 생성
        Review review = ReviewConverter.toReview(request, user, store);

        reviewRepository.save(review);
    }
}
