package umc.spring.domain.member.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.member.data.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {



}