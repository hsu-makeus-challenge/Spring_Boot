package umc.spring.domain.member.valildation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.domain.member.service.FoodCategoryQueryService;
import umc.spring.domain.member.valildation.annotation.ExistCategories;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoriesExistValidator implements ConstraintValidator<ExistCategories, List<String>> {

    private final FoodCategoryQueryService foodCategoryQueryService;

    @Override
    public void initialize(ExistCategories constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream() // 올바른 카테고리인지 조회
                .allMatch(categoryId -> foodCategoryQueryService.isExistCategory(Long.parseLong(categoryId)));

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }

}
