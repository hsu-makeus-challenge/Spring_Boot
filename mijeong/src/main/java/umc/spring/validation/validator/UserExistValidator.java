package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.ValidationService.ValidationService;
import umc.spring.validation.annotation.ExistUser;

// 검증 대상은 Long
@RequiredArgsConstructor
@Component
public class UserExistValidator implements ConstraintValidator<ExistUser,Long> {

    private final ValidationService validationService;

    @Override
    public void initialize(ExistUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // 파라미터로 넘어온 유저 아이디가 존재하는 아이디인지 검증
        boolean isValid = validationService.existsUserById(value);

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.USER_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
