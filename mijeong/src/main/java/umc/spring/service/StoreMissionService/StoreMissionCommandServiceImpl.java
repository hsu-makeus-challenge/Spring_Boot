package umc.spring.service.StoreMissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.converter.StoreMissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.repository.StoreMissionRepository.StoreMissionRepository;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.storeMission.StoreMissionResponse;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreMissionCommandServiceImpl implements StoreMissionCommandService {

    private final StoreMissionRepository storeMissionRepository;
    private final MissionQueryService missionQueryService;
    private final StoreQueryService storeQueryService;
    private final StoreMissionQueryService storeMissionQueryService;

    // 가게에 미션 추가
    @Transactional
    @Override
    public StoreMissionResponse.StoreMissionCreateResultDto saveStoreMission(Long storeId, Long missionId) {
        // 미션 조회
        Mission mission = missionQueryService.validateMission(missionId);

        // 가게 조회
        Store store = storeQueryService.validateStore(storeId);

        // 가게 미션이 존재하는 지 검증
        if (storeMissionQueryService.existsStoreMissionByMissionIdAndStoreId(storeId, missionId)) {
            throw new ErrorHandler(ErrorStatus.STORE_MISSION_EXIST);
        }

        // 가게 미션 생성 및 미션, 가게와 연관관계 설정
        StoreMission storeMission = StoreMissionConverter.toStoreMission();
        storeMission.setMission(mission);
        storeMission.setStore(store);
        storeMissionRepository.save(storeMission);

        Long storeMissionId = storeMission.getId();
        log.info("가게 미션 등록 완료, storeMissionId: {}", storeMissionId);

        return StoreMissionConverter.toStoreMissionCreateResultDto(storeMissionId);
    }
}
