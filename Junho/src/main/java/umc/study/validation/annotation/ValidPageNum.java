package umc.study.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.study.validation.validator.PageNumValidator;
import umc.study.validation.validator.StoreExistValidator;

import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션
@Constraint(validatedBy = PageNumValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPageNum {
    String message() default "Page number is less than 1";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
