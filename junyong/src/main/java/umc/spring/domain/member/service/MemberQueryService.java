package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.converter.MemberConverter;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.member.repository.phoneCertification.PhoneCertificationRepository;
import umc.spring.domain.member.web.dto.MemberResponseDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final PhoneCertificationRepository certificationRepository;

    public MemberResponseDTO.MyPageDTO findMemberInfo(Long memberId){

        // 찾는 멤버 없으면 런타임 에러 발생. 멤버 없다는 예외 문자 담음.
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("멤버 없음"));

        // 인증 테이블 조회하여 인증 여부 반환... 메서드 값이 0 이상이면 인증된거임
        boolean isCertified = certificationRepository.isCertified(member) > 0;

        // Converter로 Member객체 -> MyPageDTO로 변환. 그리고 return.
        return MemberConverter.toMyPageDTO(member, isCertified);
    }

}
