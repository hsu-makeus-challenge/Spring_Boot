package umc.spring.domain.member.repository.memberMission;

import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.mission.data.enums.MissionStatus;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<MemberMission> findMemberMissonList(MissionStatus missionStatus, int offset, int limit);
}
