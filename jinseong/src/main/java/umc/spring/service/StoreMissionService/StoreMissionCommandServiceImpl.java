package umc.spring.service.StoreMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.StoreMissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreMissionRepository.StoreMissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.MissionDTO.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreMissionCommandServiceImpl implements StoreMissionCommandService {

    private final StoreMissionRepository storeMissionRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public StoreMission createStoreMission(Long storeId, MissionRequestDTO.CreateStoreMissionDTO request) {
        StoreMission newStoreMission = StoreMissionConverter.toStoreMission(request);

        // 가게 연관관계 설정
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        newStoreMission.setStore(store);

        // 미션 연관관계 설정
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSIONS_NOT_FOUND));

        newStoreMission.setMission(mission);

        return storeMissionRepository.save(newStoreMission);
    }
}
