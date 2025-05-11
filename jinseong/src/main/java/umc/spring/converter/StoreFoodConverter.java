package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.mapping.StoreFoodCategory;
import umc.spring.domain.mapping.UserFoodCategory;

import java.util.List;
import java.util.stream.Collectors;

public class StoreFoodConverter {
    public static List<StoreFoodCategory> toStoreFoodList(List<FoodCategory> foodCategoryList) {
        return foodCategoryList.stream()
                .map(foodCategory ->
                        StoreFoodCategory.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
