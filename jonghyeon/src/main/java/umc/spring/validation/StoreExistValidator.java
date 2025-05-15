package umc.spring.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.exception.ErrorStatus;
import umc.spring.repository.StoreRepository.StoreRepository;

import java.util.List;


@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, List<Long>> {

    private final StoreRepository storeRepository;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(value -> storeRepository.existsById(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus._BAD_REQUEST.toString()).addConstraintViolation();
        }

        return isValid;

    }
}