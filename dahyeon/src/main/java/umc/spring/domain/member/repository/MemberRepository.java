package umc.spring.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.spring.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {}
