package umc.spring.domain.store.validation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.domain.store.exception.status.StoreErrorStatus;
import umc.spring.domain.store.service.StoreService;
import umc.spring.domain.store.validation.annotation.ExistStore;

import java.lang.annotation.Documented;

@Component
@RequiredArgsConstructor
public class StoresExistValidator implements ConstraintValidator<ExistStore, Long> {
    private final StoreService storeServiceImpl;
    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(StoreErrorStatus.STORE_BAD_REQUEST.toString())
                    .addConstraintViolation();
            return false;
        }

        boolean isValid = storeServiceImpl.storeExist(value);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(StoreErrorStatus.STORE_NOT_FOUND.toString())
                    .addConstraintViolation();
        }

        return isValid;
    }

}
