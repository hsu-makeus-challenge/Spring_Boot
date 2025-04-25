package umc.spring.domain.member.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.member.data.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // id로 삭제
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Member m WHERE m.id = :memberId")
    void deleteById(@Param("memberId") Long memberId);

}