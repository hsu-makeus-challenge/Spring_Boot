package umc.spring.domain.member.service;

import umc.spring.domain.member.data.Member;
import umc.spring.domain.mission.data.Mission;

public interface MemberMissionQueryService {
    boolean isExistByMemberAndMission(Member member, Mission mission);
}
