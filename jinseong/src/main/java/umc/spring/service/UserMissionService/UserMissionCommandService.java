package umc.spring.service.UserMissionService;

import umc.spring.domain.mapping.UserMission;

public interface UserMissionCommandService {
    UserMission createUserMission(Long userId, Long storeMissionId);
}
