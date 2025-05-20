package umc.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.MissionRecord;
import umc.spring.web.dto.MissionRecordRequestDTO;

public interface MissionRecordService {

     MissionRecord addMissionRecord(MissionRecordRequestDTO.addMissionRecordResultDTO request, Long missionId, Long userId);

     Page<Mission> getInProgressMissionsByUserId(Long userId, Integer page);
     //Page<Mission> getIsProgressMissionByUserId(Long userId, Integer page);
}
