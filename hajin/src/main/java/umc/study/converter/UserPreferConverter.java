package umc.study.converter;

import umc.study.domain.Food;
import umc.study.domain.mapping.FoodPreference;

import java.util.List;
import java.util.stream.Collectors;

public class UserPreferConverter {
    public static List<FoodPreference> toUserPreferList(List<Food> foodList) {
        return foodList.stream()
                .map(food -> FoodPreference.builder()
                        .food(food)
                        .build()
                ).collect(Collectors.toList());
    }
}
