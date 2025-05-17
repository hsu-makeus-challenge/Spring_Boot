package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findAllByUser(User user, PageRequest pageRequest);

    // 1. 먼저 ID만 페이징해서 조회
    @Query("SELECT r.id FROM Review r WHERE r.user = :user")
    Page<Long> findReviewIdsByUser(@Param("user") User user, Pageable pageable);

    // 2. 조회된 ID로 페치 조인 수행
    @Query("SELECT r FROM Review r LEFT JOIN FETCH r.reviewImageList WHERE r.id IN :ids")
    List<Review> findReviewsByIdWithImages(@Param("ids") List<Long> ids);
}
