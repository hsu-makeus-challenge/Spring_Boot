package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreIdHandler;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Page<Mission> getMissionList(Long StoreId, Integer page){
        Store store = storeRepository.findById(StoreId)
                .orElseThrow(() -> new StoreIdHandler(ErrorStatus.STORE_NOT_FOUND));

        Page<Mission> StoreMissionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StoreMissionPage;
    }
}
