package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository.MissionRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    // Mission 존재 여부 검증
    @Override
    public Boolean existsMissionById(Long missionId) { return missionRepository.existsById(missionId); }

    // 미션 반환
    @Override
    public Mission validateMission(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.MISSION_NOT_FOUND));
    }
}
