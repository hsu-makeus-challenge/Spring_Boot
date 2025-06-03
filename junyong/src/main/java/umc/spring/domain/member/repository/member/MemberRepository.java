package umc.spring.domain.member.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.member.data.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

}