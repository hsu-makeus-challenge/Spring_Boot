package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.repository.memberMission.MemberMissionRepository;
import umc.spring.domain.mission.data.enums.MissionStatus;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    public void findMemberMissions(MissionStatus missionStatus, int offset, int size) {

        List<MemberMission> memberMissionList = memberMissionRepository.findMemberMissonList(missionStatus, offset, size);
        memberMissionList.forEach(
                memberMission -> System.out.println("가게 이름 = " + memberMission.getMission().getStore().getName())
        );

    }
}
