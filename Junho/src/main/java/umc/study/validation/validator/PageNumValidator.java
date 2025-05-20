package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.validation.annotation.ValidPageNum;

@Component
@RequiredArgsConstructor
public class PageNumValidator implements ConstraintValidator<ValidPageNum, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value > -1;
    }

    @Override
    public void initialize(ValidPageNum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
