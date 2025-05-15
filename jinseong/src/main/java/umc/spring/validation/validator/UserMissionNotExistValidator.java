package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.service.UserMissionService.UserMissionQueryService;
import umc.spring.validation.annotation.NotExistUserMission;

@Component
@RequiredArgsConstructor
public class UserMissionNotExistValidator implements ConstraintValidator<NotExistUserMission, Long> {

    private final UserMissionQueryService userMissionQueryService;

    @Override
    public void initialize(NotExistUserMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = userMissionQueryService.existsByUserIdAndStoreMissionId(1L, value);

        // NotExist이기 때문에 존재하지 않아야 함
        // -> 존재(true)하면 오류
        // -> 존재하지 않으면(false)면 정상

        // 존재하면 오류
        if (isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.USER_MISSION_ALREADY_EXISTS.toString()).addConstraintViolation();
        }

        return !isValid;
    }
}
