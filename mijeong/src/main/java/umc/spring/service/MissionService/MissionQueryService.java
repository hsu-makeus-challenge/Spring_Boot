package umc.spring.service.MissionService;

import umc.spring.domain.Mission;

public interface MissionQueryService {

    // Mission 존재 여부 검증
    Boolean existsMissionById(Long missionId);

    // 미션 반환
    Mission validateMission(Long missionId);
}
