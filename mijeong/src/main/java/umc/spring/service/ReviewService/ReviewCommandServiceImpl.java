package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.ReviewImageConverter;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.ReviewImageRepository.ReviewImageRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final StoreQueryService storeQueryService;
    private final UserQueryService userQueryService;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    // 리뷰 등록
    @Transactional
    @Override
    public ReviewResponse.ReviewCreateResultDto saveReview(Long userId, Long storeId, ReviewRequest.ReviewCreateDto requestDto) {
        User user = userQueryService.validateUser(userId);
        Store store = storeQueryService.validateStore(storeId);

        // Review 객체 생성 및 저장
        Review review = ReviewConverter.toReview(requestDto);
        // 유저, 가게와 연관관계 설정
        review.setUser(user);
        review.setStore(store);
        reviewRepository.save(review);

        // ReviewImage 객체 생성 및 저장
        List<ReviewImage> reviewImageList = ReviewImageConverter.toReviewImageList(requestDto.getReviewImages());
        // 리뷰 이미지에 리뷰 매핑
        reviewImageList.forEach(reviewImage -> reviewImage.setReview(review));
        reviewImageRepository.saveAll(reviewImageList);

        Long reviewId = review.getId();
        log.info("리뷰 등록 완료, reviewId: {}", reviewId);
        return ReviewConverter.toReviewCreateResultDto(reviewId);
    }
}
