package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.mission.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Mission addMission(MissionRequestDTO.AddMissionDto request, Long storeId){

        // store 존재 여부
        Store store = storeRepository.findById(storeId).orElse(null);

        Mission mission = MissionConverter.toMission(request, store);

        return missionRepository.save(mission);
    }

}
