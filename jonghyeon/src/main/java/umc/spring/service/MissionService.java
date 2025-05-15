package umc.spring.service;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionService {
    Mission addMission(MissionRequestDTO.addMissionDTO request, Long storeId);
}
