package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.repository.memberMission.MemberMissionRepository;
import umc.spring.domain.mission.data.Mission;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isExistByMemberAndMission(Member member, Mission mission) {
        return memberMissionRepository.findByMissionAndMember(mission, member).orElse(null) == null;
    }
}
