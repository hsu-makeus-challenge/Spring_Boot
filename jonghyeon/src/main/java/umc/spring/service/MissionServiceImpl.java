package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
import umc.spring.converter.MissionConveter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission addMission(MissionRequestDTO.addMissionDTO request, Long storeId) {
        Optional<Store> store = storeRepository.findById(storeId);

        Mission newMission = MissionConveter.toMission(request, store.orElse(null));

        return missionRepository.save(newMission);
    }

    @Transactional(readOnly = true)
    public Page<Mission> getAllMissionsByStoreId(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        return missionRepository.findAllByStore(store, PageRequest.of(page, 10));
    }

}
