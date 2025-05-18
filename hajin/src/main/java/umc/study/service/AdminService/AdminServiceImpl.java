package umc.study.service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.repository.AdminRepository.AdminRepository;
import umc.study.repository.MissionRepository.MissionRepositoryImpl;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final StoreRepository storeRepository;

    @Override
    public Mission saveMission(Long storeId, MissionRequestDTO.MissionDto request){
        // 관리자 인증을 여기서 하는건가..?
        // 일단 패스

        Store store = storeRepository.getReferenceById(storeId);

        Mission mission = MissionConverter.toMission(store, request);
        //mission.setStore(store);
        store.addMission(mission);
        return adminRepository.save(mission);
    }
}
