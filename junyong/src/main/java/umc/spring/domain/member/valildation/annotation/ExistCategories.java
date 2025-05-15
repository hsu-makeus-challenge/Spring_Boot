package umc.spring.domain.member.valildation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.domain.member.valildation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CategoriesExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니디다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
