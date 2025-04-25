package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.enums.Gender;
import umc.spring.domain.member.data.enums.SocialType;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.member.repository.memberPrefer.MemberPreferRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberTestCommandService {

    private final MemberRepository memberRepository;
    private final MemberPreferRepository memberPreferRepository;

    @Transactional
    public void addTestMember() {
        Member member = Member.builder()
                .name("name1")
                .address("address1")
                .specAddress("address2")
                .gender(Gender.MALE)
                .socialType(SocialType.KAKAO)
                .birth("20020413")
                .phone("01011112222")
                .email("kakao@gmail.com")
                .locationAgree(true)
                .marketingAgree(true)
                .build();
        memberRepository.save(member);
    }

}
