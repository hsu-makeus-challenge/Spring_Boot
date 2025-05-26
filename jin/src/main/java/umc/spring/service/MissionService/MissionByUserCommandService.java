package umc.spring.service.MissionService;

import umc.spring.domain.mapping.MissionByUser;

public interface MissionByUserCommandService {
    public MissionByUser challengeMission(Long missionId);
//    public MissionByUser completeMission(Long userId, Long missionId, Boolean isCompleted);
}
