package umc.spring.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.repository.MissionRepository;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.review.repository.ReviewRepository;
import umc.spring.domain.store.converter.StoreConverter;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.repository.StoreRepository;
import umc.spring.domain.store.web.dto.StoreResponseDTO;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        filteredStores.forEach(store -> System.out.println("Store: " + store));
        return filteredStores;
    }

    @Override
    public Boolean isExistStore(Long id) {
        // id 통해 가게가 존재하는지 조회
        return storeRepository.findById(id).orElse(null) != null;
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));

        return storePage;
    }

    @Override
    public StoreResponseDTO.MissionListDto getMissionList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> missionList = missionRepository.findAllByStore(store, pageRequest);
        // page >= missionList.getTotalPages() -> page가 총 페이지 수를 넘어갔을 때 오류 발생
        // missionList.getTotalElements() != 0 -> 찾은 미션 개수가 0일 때는 오류 발생하지 않도록 함
        if(page >= missionList.getTotalPages() && missionList.getTotalElements() != 0) {
            throw new ErrorHandler(ErrorStatus.PAGE_OUT_OF_RANGE);
        }

        return StoreConverter.toMissionListDto(missionList);
    }
}
