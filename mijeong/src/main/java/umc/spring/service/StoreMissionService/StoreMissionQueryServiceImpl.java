package umc.spring.service.StoreMissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.repository.StoreMissionRepository.StoreMissionRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreMissionQueryServiceImpl implements StoreMissionQueryService {
    private final StoreMissionRepository storeMissionRepository;

    // StoreMission 존재 여부 검증
    @Override
    public Boolean existStoreMissionById(Long storeMissionId) {
        return storeMissionRepository.existsById(storeMissionId);
    }

    // 미션 아이디와 가게 아이디를 통해 StoreMission 존재 여부 검증
    @Override
    public Boolean existsStoreMissionByMissionIdAndStoreId(Long missionId, Long storeId) {
        return storeMissionRepository.existsByMissionIdAndStoreId(missionId, storeId);
    }

    // 가게 미션 반환
    @Override
    public StoreMission validateStoreMission(Long storeMissionId) {
        return storeMissionRepository.findById(storeMissionId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.STORE_MISSION_NOT_FOUND));
    }
}