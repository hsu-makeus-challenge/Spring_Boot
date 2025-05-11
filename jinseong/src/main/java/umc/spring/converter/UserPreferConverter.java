package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.mapping.UserFoodCategory;

import java.util.List;
import java.util.stream.Collectors;

public class UserPreferConverter {
    public static List<UserFoodCategory> toUserPreferList(List<FoodCategory> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        UserFoodCategory.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
