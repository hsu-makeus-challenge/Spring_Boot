package umc.spring.domain.member.repository.memberMission;

import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.domain.region.data.Region;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<MemberMission> findMemberMissonList(MissionStatus missionStatus, int offset, int limit);
    List<MemberMission> findMemberMissionByRegion(Region region, MissionStatus status);
}
