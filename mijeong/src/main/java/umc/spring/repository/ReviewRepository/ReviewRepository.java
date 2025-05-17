package umc.spring.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 가게와 페이지 번호로 리뷰 목록 조회 (페이지네이션)
    Page<Review> findAllByStore(Store store, Pageable pageRequest);
}
