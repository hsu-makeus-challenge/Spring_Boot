package umc.spring.domain.mission.service;

import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.web.dto.MissionRequestDTO;

public interface MissionCommandService {

    public Mission addMission(MissionRequestDTO.addDto request, Long storeId);

}
