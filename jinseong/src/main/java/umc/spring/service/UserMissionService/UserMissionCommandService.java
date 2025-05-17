package umc.spring.service.UserMissionService;

import jakarta.validation.constraints.NotBlank;
import umc.spring.domain.mapping.UserMission;

public interface UserMissionCommandService {
    UserMission createUserMission(Long userId, Long storeMissionId);

    UserMission completeUserMission(Long userMissionId, String code);
}
