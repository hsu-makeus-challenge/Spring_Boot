package umc.spring.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import umc.spring.domain.member.entity.Agreement;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {}
