package umc.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.MissionRecord;
import umc.spring.web.dto.MissionRecordRequestDTO;

public interface MissionRecoredService {
     MissionRecord addMissionRecord(MissionRecordRequestDTO.addMissionRecordResultDTO request, Long missionId,Long userId);

     Page<MissionRecord> getAllMissionByUserId(Long userId, Integer page);
}
