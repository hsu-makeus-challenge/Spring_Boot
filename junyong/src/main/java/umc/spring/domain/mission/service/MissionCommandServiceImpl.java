package umc.spring.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.mission.converter.MissionConverter;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.repository.MissionRepository;
import umc.spring.domain.mission.web.dto.MissionRequestDTO;
import umc.spring.domain.region.data.Region;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Mission addMission(MissionRequestDTO.addDto request, Long storeId) {

        Store store = storeRepository.findById(storeId).orElse(null);
        Region region = store.getRegion();
        Mission mission = MissionConverter.toMission(request, region, store);
        return missionRepository.save(mission);
    }
}
