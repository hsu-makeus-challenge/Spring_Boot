package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.NeighborhoodHandler;
import umc.spring.converter.*;
import umc.spring.domain.*;
import umc.spring.domain.mapping.StoreFoodCategory;
import umc.spring.domain.mapping.UserFoodCategory;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.NeighborhoodRepository;
import umc.spring.repository.StoreHoursRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.StoreDTO.StoreRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final StoreHoursRepository storeHoursRepository;

    @Override
    public Store createStore(StoreRequestDTO.CreateStoreDto request) {

        Store newStore = StoreConverter.toStore(request);

        // 지역 연관관계 설정
        Neighborhood neighborhood = neighborhoodRepository.findById(request.getNeighborhood())
                .orElseThrow(() -> new NeighborhoodHandler(ErrorStatus.NEIGHBORHOOD_NOT_FOUND));

        newStore.setNeighborhood(neighborhood);


        // 음식 카테고리 연관관계 설정
        List<FoodCategory> foodCategoryList = request.getFoodCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<StoreFoodCategory> storeFoodList = StoreFoodConverter.toStoreFoodList(foodCategoryList);

        storeFoodList.forEach(storeFood -> {storeFood.setStore(newStore);});

        // 영업시간 연관관계 설정
        List<StoreHours> storeHoursList = StoreHoursConverter.toStoreHours(request.getOpenHours(), request.getCloseHours());
        storeHoursList.forEach(storeHours -> {storeHours.setStore(newStore);});

        return storeRepository.save(newStore);
    }
}
