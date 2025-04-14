package umc.spring.domain.member.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import umc.spring.domain.member.entity.enums.FoodCategory;

@Converter
public class FoodCategoryConverter implements AttributeConverter<List<FoodCategory>, String> {

  private static final String DELIMITER = ",";

  @Override
  public String convertToDatabaseColumn(List<FoodCategory> attribute) {
    if (attribute == null || attribute.isEmpty()) return "";
    return attribute.stream().map(Enum::name).collect(Collectors.joining(DELIMITER));
  }

  @Override
  public List<FoodCategory> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.isEmpty()) return new ArrayList<>();
    return Arrays.stream(dbData.split(DELIMITER))
        .map(FoodCategory::valueOf)
        .collect(Collectors.toList());
  }
}
