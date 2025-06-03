package umc.spring.converter;

import umc.spring.domain.Category;
import umc.spring.domain.Preference;

import java.util.List;
import java.util.stream.Collectors;

public class UserPreferConverter {
    public static List<Preference> toUserPreferList(List<Category> CategoryList){

        return CategoryList.stream()
                .map( category ->
                        Preference.builder()
                                .category(category)
                                .build()
                ).collect(Collectors.toList());
    }
}
