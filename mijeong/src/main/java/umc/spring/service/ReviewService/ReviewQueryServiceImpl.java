package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.web.dto.review.ReviewResponse;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final StoreQueryService storeQueryService;
    private final UserQueryService userQueryService;
    private final ReviewRepository reviewRepository;

    private static final Integer PAGE_SIZE=10;

    // 페이지 정렬, 최신순
    private Pageable pageRequest(Integer pageNumber) {
        return PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("createdAt").descending());
    }

    // 가게의 리뷰 목록을 페이지네이션으로 조회
    @Override
    public Page<Review> findStoreReviewPage(Long storeId, Integer page) {
        // 가게 조회
        Store store = storeQueryService.validateStore(storeId);

        Page<Review> storeReviewPage = reviewRepository.findAllByStore(store, pageRequest(page));

        return storeReviewPage;
    }

    // 유저의 리뷰 목록을 페이지네이션으로 조회
    @Override
    public ReviewResponse.UserReviewPreViewListDto findUserReviewPage(Long userId, Integer page) {
        // 유저 조회
        User user = userQueryService.validateUser(userId);

        // 페이지네이션으로 유저의 리뷰를 이미지와 함께 조회
        Page<Review> userReviewPage = reviewRepository.findAllByUserWithImage(userId, pageRequest(page));

        // 시간 측정 시작
        long start = System.currentTimeMillis();

        ReviewResponse.UserReviewPreViewListDto result =
                ReviewConverter.toUserReviewPreViewListDto(userReviewPage, user.getNickName());

        long end = System.currentTimeMillis();
        log.info("소요 시간: {} ns (약 {} ms)", (end - start), (end - start) / 1_000_000.0);

        return result;
    }

    // 시니어 미션 - 유저의 리뷰 목록을 슬라이스로 조회
    @Override
    public ReviewResponse.UserReviewPreViewSliceDto findUserReviewPageWithSlice(Long userId, Integer page) {
        // 유저 조회
        User user = userQueryService.validateUser(userId);

        // 슬라이스로 유저의 리뷰를 이미지와 함께 조회
        Slice<Review> userRevieSlice = reviewRepository.findAllByUserWithImageAndSlice(userId, pageRequest(page));

        return ReviewConverter.toUserReviewPreViewSliceDto(userRevieSlice, user.getNickName());
    }


}
