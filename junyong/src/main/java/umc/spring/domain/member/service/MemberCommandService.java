package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberStore;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.member.repository.memberPrefer.MemberPreferRepository;
import umc.spring.domain.member.repository.memberStore.MemberStoreRepository;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.repository.StoreRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final MemberPreferRepository memberPreferRepository;
    private final MemberStoreRepository memberStoreRepository;

    private final StoreRepository storeRepository;

    @Transactional
    public void deleteMember() {

        // 아무 유저 하나 반환
        Member member = memberRepository.findAll().stream().findAny().orElse(null);

        log.info("member의 선호 음식 카테고리 데이터 삭제");
        memberPreferRepository.deleteAllById(member);
        log.info("member 삭제");
        memberRepository.deleteById(member.getId());

    }

    @Transactional
    public void scrapStore(Long storeId) {

        log.info("storeId = {}을 스크랩합니다.", storeId);

        Member member = memberRepository.findAll().stream().findAny().orElse(null); // 아무 member 가져옴
        Optional<MemberStore> isScraped = memberStoreRepository.findByMemberIdAndStoreId(storeId, storeId); // member와 storeId로 스크랩 여부 파악
        Store store = storeRepository.findById(storeId).orElse(null); // store로 store 찾음

        // 이미 스크랩했다면 리턴
        if(isScraped.isPresent()) {
            log.info("{}을 이미 스크랩함", store.getName());
            return;
        }

        // 스크랩되지 않았을 때
        MemberStore memberStore = MemberStore.builder() // 스크랩 정보 생성
                .member(member)
                .store(store)
                .build();

        memberStoreRepository.save(memberStore); // 스크랩 정보 저장
        log.info("{} 가게 스크랩을 완료했습니다.", store.getName());
    }

}
