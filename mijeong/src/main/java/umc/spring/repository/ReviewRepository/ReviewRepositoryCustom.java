package umc.spring.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Review;
import umc.spring.domain.User;

public interface ReviewRepositoryCustom {
    // 페이지 번호로 유저의 리뷰 목록 조회 (페이지네이션)
    Page<Review> findAllByUserWithImage(User user, Pageable pageRequest);
}
