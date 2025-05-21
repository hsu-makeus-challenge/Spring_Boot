package umc.spring.service;


import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionService {
    Mission addMission(MissionRequestDTO.addMissionDTO request, Long store);

    Page<Mission> getAllMissionsByStoreId(Long storeId, Integer page);
}
