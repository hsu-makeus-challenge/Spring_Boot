package umc.spring.domain.member.repository.phoneCertification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.PhoneCertification;

public interface PhoneCertificationRepository extends JpaRepository<PhoneCertification, Long> {

    // QueryDSL 써도 됐는데, jpql 쓰고싶었음
    @Query("select count(*) from PhoneCertification pc " +
            "where pc.member = :member and pc.isValid = true")
    int isCertified(@Param("member") Member member);

}