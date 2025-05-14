package umc.spring.domain.store.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.domain.store.validation.validator.StoresExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoresExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {
    String message() default "존재하지 않는 가게Id 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
