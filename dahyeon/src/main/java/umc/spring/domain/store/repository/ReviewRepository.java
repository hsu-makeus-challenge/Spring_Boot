package umc.spring.domain.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.spring.domain.member.entity.Member;
import umc.spring.domain.store.entity.Review;
import umc.spring.domain.store.entity.Store;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  @Modifying(clearAutomatically = true)
  @Query("DELETE FROM Review r WHERE r.member = :member")
  void deleteByMember(@Param("member") Member member);

  boolean existsByMemberId(Long memberId);

  @Query("SELECT COALESCE(AVG(r.score), 0.0) FROM Review r WHERE r.store = :store")
  Float calculateAverageScore(@Param("store") Store store);

  Page<Review> findAllByStoreId(Long storeId, PageRequest pageRequest);

  Page<Review> findAllByMemberId(Long memberId, Pageable pageable);
}
