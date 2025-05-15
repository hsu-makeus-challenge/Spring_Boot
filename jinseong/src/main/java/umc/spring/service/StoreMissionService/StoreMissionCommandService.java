package umc.spring.service.StoreMissionService;

import umc.spring.domain.mapping.StoreMission;
import umc.spring.web.dto.MissionDTO.MissionRequestDTO;

public interface StoreMissionCommandService {
    StoreMission createStoreMission(Long storeId, MissionRequestDTO.CreateStoreMissionDTO request);
}
