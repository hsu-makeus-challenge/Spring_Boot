package umc.spring.domain.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.spring.domain.question.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
  @Modifying
  @Query(
      "DELETE FROM Answer a WHERE a.questionId IN (SELECT q.id FROM Question q WHERE q.memberId = :memberId)")
  void deleteByMemberId(@Param("memberId") Long memberId);
}
