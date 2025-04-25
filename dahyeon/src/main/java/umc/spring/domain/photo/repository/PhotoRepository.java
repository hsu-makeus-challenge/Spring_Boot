package umc.spring.domain.photo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.spring.domain.photo.entity.Photos;

public interface PhotoRepository extends JpaRepository<Photos, Long> {
  @Modifying(clearAutomatically = true)
  @Query("DELETE FROM Photos p WHERE p.memberId = :memberId")
  void deleteByMemberId(@Param("memberId") Long memberId);

  boolean existsByMemberId(Long memberId);
}
