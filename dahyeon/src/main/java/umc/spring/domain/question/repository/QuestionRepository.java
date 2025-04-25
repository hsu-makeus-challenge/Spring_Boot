package umc.spring.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.spring.domain.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
  @Modifying
  @Query("DELETE FROM Question q WHERE q.memberId = :memberId")
  void deleteByMemberId(@Param("memberId") Long memberId);

  boolean existsByMemberId(Long memberId);
}
