package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.CategoriesExistValidator;


import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션
@Constraint(validatedBy = CategoriesExistValidator.class) // Documented로 validation을 할 수 있도록 제공하는 어노테이션
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER }) //어노테이션 적용 범위 지정
@Retention(RetentionPolicy.RUNTIME) // 생명주기 지정
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}