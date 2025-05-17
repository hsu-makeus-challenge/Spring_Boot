package umc.spring.service.StoreMissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.converter.StoreMissionConverter;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.repository.StoreMissionRepository.StoreMissionRepository;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.storeMission.StoreMissionResponse;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreMissionQueryServiceImpl implements StoreMissionQueryService {
    private final StoreMissionRepository storeMissionRepository;
    private final StoreQueryService storeQueryService;

    private static final Integer PAGE_SIZE=10;

    // 페이지 정렬, 최신순
    private Pageable pageRequest(Integer pageNumber) {
        return PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("createdAt").descending());
    }

    // StoreMission 존재 여부 검증
    @Override
    public Boolean existStoreMissionById(Long storeMissionId) {
        return storeMissionRepository.findById(storeMissionId).isPresent();
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

    // 가게의 미션 목록을 페이지네이션으로 조회
    @Override
    public StoreMissionResponse.StoreMissionInfoListDto findStoreMissionPage(Long storeId, Integer page) {
        // 가게 조회
        Store store = storeQueryService.validateStore(storeId);

        // 페이지네이션으로 가게의 미션을 미션과 함께 조회
        Page<StoreMission> storeMissionPage = storeMissionRepository.findAllByStoreWithMission(store, pageRequest(page));

        return StoreMissionConverter.toStoreMissionInfoListDto(storeMissionPage, store.getStoreName());
    }
}