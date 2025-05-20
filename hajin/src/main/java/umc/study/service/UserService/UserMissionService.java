package umc.study.service.UserService;

import org.springframework.stereotype.Service;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.UserMissionRequestDTO;

@Service
public interface UserMissionService {
    public UserMission addMission(Integer userId, Long missionId, UserMissionRequestDTO.UserMissionDto request);
}
