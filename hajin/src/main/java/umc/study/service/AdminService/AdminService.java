package umc.study.service.AdminService;

import umc.study.domain.Mission;
import umc.study.web.dto.MissionRequestDTO;

public interface AdminService {
    public Mission saveMission(Long storeId, MissionRequestDTO.MissionDto request);
}
