package umc.spring.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.spring.domain.member.entity.Member;
import umc.spring.domain.store.entity.StoreReview;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {
  @Modifying(clearAutomatically = true)
  @Query(
      "DELETE FROM StoreReview s WHERE s.reviewId IN (SELECT r.id FROM Review r WHERE r.member = :member)")
  void deleteByMember(@Param("member") Member member);
}
