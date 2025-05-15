package umc.spring.service.StoreMissionService;

import umc.spring.web.dto.storeMission.StoreMissionResponse;

public interface StoreMissionCommandService {

    // 가게에 미션 추가
    StoreMissionResponse.StoreMissionCreateResultDto saveStoreMission(Long storeId, Long missionId);
}
