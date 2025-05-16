package umc.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;

public interface ReviewQueryService {

    // 가게의 리뷰 목록을 페이지네이션으로 조회
    Page<Review> findReviewList(Long storeId, Integer page);
}
