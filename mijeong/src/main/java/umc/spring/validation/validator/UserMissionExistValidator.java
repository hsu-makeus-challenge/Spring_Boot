package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.UserMissionService.UserMissionQueryService;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.validation.annotation.ExistUserMission;

// 검증 대상은 Long
@Slf4j
@RequiredArgsConstructor
@Component
public class UserMissionExistValidator implements ConstraintValidator<ExistUserMission,Long> {

    private final UserMissionQueryService userMissionQueryService;

    @Override
    public void initialize(ExistUserMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        // 파라미터로 넘어온 유저 아이디가 존재하는 아이디인지 검증
        boolean isValid = userMissionQueryService.existsUserMissionById(value);
        log.info("ExistUserMission userId: {}, isValid: {}", value, isValid);

        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.USER_MISSION_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}