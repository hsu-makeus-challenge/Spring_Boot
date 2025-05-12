package umc.spring.service.StoreMissionService;

public interface StoreMissionQueryService {

    // 미션 아이디와 가게 아이디를 통해 StoreMission 존재 여부 검증
    Boolean existsStoreMissionByMissionIdAndStoreId(Long missionId, Long storeId);
}
