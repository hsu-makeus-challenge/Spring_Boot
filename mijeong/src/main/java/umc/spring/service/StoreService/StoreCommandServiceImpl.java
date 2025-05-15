package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.converter.StoreFoodCategoryConverter;
import umc.spring.converter.StoreImageConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.StoreImage;
import umc.spring.domain.mapping.StoreFoodCategory;
import umc.spring.repository.StoreFoodCategoryRepository.StoreFoodCategoryRepository;
import umc.spring.repository.StoreImageRepository.StoreImageRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.service.FoodCategoryService.FoodCategoryQueryService;
import umc.spring.service.RegionService.RegionQueryService;
import umc.spring.web.dto.store.StoreRequest;
import umc.spring.web.dto.store.StoreResponse;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final StoreImageRepository storeImageRepository;
    private final StoreFoodCategoryRepository storeFoodCategoryRepository;
    private final RegionQueryService regionQueryService;
    private final FoodCategoryQueryService foodCategoryQueryService;

    // 가게 등록
    @Transactional
    @Override
    public StoreResponse.StoreCreateResultDto saveStore(Long regionId, StoreRequest.StoreCreateDto requestDto) {
        Region region = regionQueryService.validateRegion(regionId);

        // 가게 생성 및 저장
        Store store = StoreConverter.toStore(requestDto);
        // 지역과 연관관계 설정
        store.setRegion(region);
        storeRepository.save(store);

        // StoreImage 객체 생성 및 저장
        List<StoreImage> storeImageList = StoreImageConverter.toStoreImageList(requestDto.getStoreImages());
        // 가게 이미지에 가게 매핑
        storeImageList.forEach(storeImage -> storeImage.setStore(store));
        storeImageRepository.saveAll(storeImageList);

        // 음식 카테고리 추출
        List<Long> categoryIds = requestDto.getStoreFoodCategories();
        List<FoodCategory> foodCategoryList = foodCategoryQueryService.getFoodCategoryList(categoryIds);

        // 음식 카테고리를 가게 음식 카테고리로 매핑
        List<StoreFoodCategory> storeFoodCategoryList = StoreFoodCategoryConverter.toStoreFoodCategoryList(foodCategoryList);
        // 유저 음식 카테고리에 유저 매핑
        storeFoodCategoryList.forEach(storeFoodCategory-> {storeFoodCategory.setStore(store);});
        storeFoodCategoryRepository.saveAll(storeFoodCategoryList);

        Long storeId = store.getId();
        log.info("가게 등록 완료, storeId: {}", storeId);
        return StoreConverter.toStoreCreateResultDto(storeId);
    }
}
