package umc.spring.service.UserMissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.UserMission;

public interface UserMissionQueryService {
    Page<UserMission> findMissionByUserIdAndUserMissionStatus(Long userId, UserMissionStatus status, int page, int size);
}
