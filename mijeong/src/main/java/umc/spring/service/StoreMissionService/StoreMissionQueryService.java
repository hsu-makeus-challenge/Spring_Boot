package umc.spring.service.StoreMissionService;

import umc.spring.domain.mapping.StoreMission;

public interface StoreMissionQueryService {

    // StoreMission 존재 여부 검증
    Boolean existStoreMissionById(Long storeMissionId);

    // 미션 아이디와 가게 아이디를 통해 StoreMission 존재 여부 검증
    Boolean existsStoreMissionByMissionIdAndStoreId(Long missionId, Long storeId);

    // 가게 미션 반환
    StoreMission validateStoreMission(Long storeMissionId);
}
