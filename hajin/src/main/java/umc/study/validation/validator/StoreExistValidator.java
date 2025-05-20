package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.validation.annotation.ExistCategories;
import umc.study.validation.annotation.ExistStore;

import java.util.List;

public class StoreExistValidator implements ConstraintValidator<ExistStore, List<Long>> {

    private final StoreRepository storeRepository;

    public StoreExistValidator(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

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
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }
}
