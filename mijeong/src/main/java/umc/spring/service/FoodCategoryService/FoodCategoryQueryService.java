package umc.spring.service.FoodCategoryService;

import umc.spring.domain.FoodCategory;

import java.util.List;

public interface FoodCategoryQueryService {
    // 음식 카테고리 추출
    List<FoodCategory> getFoodCategoryList(List<Long> categoryIds);

    // 아이디를 통해 존재하는 음식 카테고리인지 확인
    Boolean existsFoodCategoryById(Long foodCategoryId);
}
