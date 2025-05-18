package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.Users;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review save(Long userId, Long storeId, ReviewRequestDTO.ReviewDto request) {
        // 사용자 조회, 1번 사용자만 리뷰 작성
        Users user = userRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No users available"));

        Store store = storeRepository.getReferenceById(storeId);
        // 리뷰 생성
        Review review = ReviewConverter.toReview(user,request);
        review.setUser(user); // 사용자 정보 매핑 , 근데 위에서 user을 한번 넣어서 필요 없는거 같기도 한데
        // 물어보기

        review.setStore(store);
        store.addReview(review);
        return reviewRepository.save(review);
    }
}
