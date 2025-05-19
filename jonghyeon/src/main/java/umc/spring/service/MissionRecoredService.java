package umc.spring.service;

import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.MissionRecord;
import umc.spring.web.dto.MissionRecordRequestDTO;

public interface MissionRecoredService {


     MissionRecord addMissionRecord(MissionRecordRequestDTO.addMissionRecordResultDTO request, Long missionId,Long userId);
}
