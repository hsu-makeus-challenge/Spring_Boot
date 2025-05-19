package umc.spring.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.exception.ErrorStatus;
import umc.spring.repository.UserRepository;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PageExistValidator implements ConstraintValidator<ExistPage, Integer> {

    @Override
    public void initialize(ExistPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if (value == null || value < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    ErrorStatus.INVALID_PAGE.name()
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
}