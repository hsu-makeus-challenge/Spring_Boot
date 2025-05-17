package umc.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewResponse;

public interface ReviewQueryService {

    // 가게의 리뷰 목록을 페이지네이션으로 조회
    Page<Review> findStoreReviewPage(Long storeId, Integer page);

    // 유저의 리뷰 목록을 페이지네이션으로 조회
    ReviewResponse.UserReviewPreViewListDto findUserReviewPage(Long userId, Integer page);
}
