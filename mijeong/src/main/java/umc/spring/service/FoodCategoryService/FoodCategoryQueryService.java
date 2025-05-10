package umc.spring.service.FoodCategoryService;

import umc.spring.domain.FoodCategory;

import java.util.List;

public interface FoodCategoryQueryService {
    // 음식 카테고리 추출
    List<FoodCategory> getFoodCategoryList(List<Long> categoryIds);
}
