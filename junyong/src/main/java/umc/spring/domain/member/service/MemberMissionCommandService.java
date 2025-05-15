package umc.spring.domain.member.service;

import umc.spring.domain.member.data.mapping.MemberMission;

public interface MemberMissionCommandService {
    public MemberMission challengeMission(Long missionId);
}
