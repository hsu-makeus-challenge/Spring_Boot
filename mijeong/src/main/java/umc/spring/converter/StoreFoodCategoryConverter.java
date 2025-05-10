package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.mapping.StoreFoodCategory;

import java.util.List;
import java.util.stream.Collectors;

public class StoreFoodCategoryConverter {

    // 음식 카테고리를 가게 음식 카테고리에 매핑
    public static List<StoreFoodCategory> toStoreFoodCategoryList(List<FoodCategory> foodCategoryList){
        return foodCategoryList.stream()
                .map(foodCategory ->
                        StoreFoodCategory.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
