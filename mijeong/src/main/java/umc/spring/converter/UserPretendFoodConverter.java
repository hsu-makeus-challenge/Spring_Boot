package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.mapping.UserPretendFood;

import java.util.List;
import java.util.stream.Collectors;

public class UserPretendFoodConverter {

    // 음식 카테고리를 유저 음식 카테고리에 매핑
    public static List<UserPretendFood> toUserPretendFoodList(List<FoodCategory> foodCategoryList){
        return foodCategoryList.stream()
                .map(foodCategory ->
                        UserPretendFood.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
