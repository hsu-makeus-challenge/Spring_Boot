package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.ValidationService.ValidationService;
import umc.spring.validation.annotation.ExistStore;

// 검증 대상은 Long
@RequiredArgsConstructor
@Component
public class StoreExistValidator implements ConstraintValidator<ExistStore,Long> {

    private final ValidationService validationService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // 파라미터로 넘어온 가게 아이디가 존재하는 아이디인지 검증
        boolean isValid = validationService.existsStoreById(value);

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
