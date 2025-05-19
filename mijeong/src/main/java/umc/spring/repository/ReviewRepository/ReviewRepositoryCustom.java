package umc.spring.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import umc.spring.domain.Review;

public interface ReviewRepositoryCustom {
    // 페이지 번호로 유저의 리뷰 목록 조회 (페이지네이션)
    Page<Review> findAllByUserWithImage(Long userId, Pageable pageRequest);

    // 페이지 번호로 유저의 리뷰 목록 조회 (슬라이스)
    Slice<Review> findAllByUserWithImageAndSlice(Long userId, Pageable pageable);
}
