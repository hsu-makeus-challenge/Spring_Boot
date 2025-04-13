package umc.spring.domain.member.repository.memberPrefer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberPrefer;

public interface MemberPreferRepository extends JpaRepository<MemberPrefer, Long> {

    // id로 여러 데이터 삭제
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM MemberPrefer mp WHERE mp.member = :member")
    void deleteAllById(@Param("member") Member member);

}