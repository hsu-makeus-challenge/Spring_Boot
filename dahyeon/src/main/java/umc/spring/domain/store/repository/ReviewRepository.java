package umc.spring.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import umc.spring.domain.store.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  @Modifying(clearAutomatically = true)
  @Query("DELETE FROM Review r WHERE r.memberId = :memberId")
  void deleteByMemberId(Long memberId);

  boolean existsByMemberId(Long memberId);
}
