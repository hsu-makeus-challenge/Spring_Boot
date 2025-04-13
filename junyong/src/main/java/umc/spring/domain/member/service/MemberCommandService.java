package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.member.repository.memberPrefer.MemberPreferRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberPreferRepository memberPreferRepository;

    @Transactional
    public void deleteMember() {

        // 아무 유저 하나 반환
        Member member = memberRepository.findAll().stream().findAny().orElse(null);

        log.info("member의 선호 음식 카테고리 데이터 삭제");
        memberPreferRepository.deleteAllById(member);
        log.info("member 삭제");
        memberRepository.deleteById(member.getId());

    }

}
