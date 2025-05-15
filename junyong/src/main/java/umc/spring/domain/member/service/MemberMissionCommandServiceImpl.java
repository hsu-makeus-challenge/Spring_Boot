package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.member.converter.MemberMissionConverter;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.member.repository.memberMission.MemberMissionRepository;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.repository.MissionRepository;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.MissionHandler;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMission challengeMission(Long missionId) {

        Member member = memberRepository.findById(1L).orElse(null);
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);
        memberMission.setMission(mission);
        memberMission.setMember(member);

        return memberMissionRepository.save(memberMission);
    }
}
