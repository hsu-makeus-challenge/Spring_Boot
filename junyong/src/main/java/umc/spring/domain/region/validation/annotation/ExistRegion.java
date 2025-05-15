package umc.spring.domain.region.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.domain.region.validation.validator.RegionExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegionExistValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistRegion {

    String message() default "해당하는 지역이 존재하지 않습니디다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
