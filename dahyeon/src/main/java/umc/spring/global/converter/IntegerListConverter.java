package umc.spring.global.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IntegerListConverter implements AttributeConverter<List<Integer>, String> {

  private static final String DELIMITER = ",";

  @Override
  public String convertToDatabaseColumn(List<Integer> attribute) {
    return (attribute != null && !attribute.isEmpty())
        ? attribute.stream().map(String::valueOf).collect(Collectors.joining(DELIMITER))
        : "";
  }

  @Override
  public List<Integer> convertToEntityAttribute(String dbData) {
    return (dbData != null && !dbData.isBlank())
        ? Arrays.stream(dbData.split(DELIMITER)).map(Integer::valueOf).collect(Collectors.toList())
        : new ArrayList<>();
  }
}
