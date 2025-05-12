package umc.spring.service.StoreMissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.StoreMissionRepository.StoreMissionRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreMissionQueryServiceImpl implements StoreMissionQueryService {
    private final StoreMissionRepository storeMissionRepository;

    // 미션 아이디와 가게 아이디를 통해 StoreMission 존재 여부 검증
    @Override
    public Boolean existsStoreMissionByMissionIdAndStoreId(Long missionId, Long storeId) {
        return storeMissionRepository.existsByMissionIdAndStoreId(missionId, storeId);
    }
}