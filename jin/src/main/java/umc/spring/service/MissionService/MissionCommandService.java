package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.MissionRequestDTO;

public interface MissionCommandService {
    Mission addMission(MissionRequestDTO.AddMissionDto request, Long storeId);
}
