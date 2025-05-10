package umc.spring.domain.member.converter;

import umc.spring.domain.member.data.FoodCategory;
import umc.spring.domain.member.data.mapping.MemberPrefer;

import java.util.List;

public class MemberPreferConverter {

    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList) {
        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).toList();
    }

}
