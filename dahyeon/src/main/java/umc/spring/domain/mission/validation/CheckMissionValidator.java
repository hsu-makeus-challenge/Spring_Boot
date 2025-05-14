package umc.spring.domain.mission.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.domain.mission.exception.status.MissionErrorStatus;
import umc.spring.domain.mission.service.MissionService;

@Component
@RequiredArgsConstructor
public class CheckMissionValidator implements ConstraintValidator<CheckMission, Long>{
    private final MissionService missionServiceImpl;

    @Override
    public void initialize(CheckMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        Long memberId = getCurrentMemberId();
        if (missionId == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MissionErrorStatus.MISSION_NOT_FOUND.toString()).addConstraintViolation();

        }
        boolean isValid = missionServiceImpl.checkMissionChallenge(memberId, missionId);
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MissionErrorStatus.MISSION_ALREADY_CHALLENGING.toString()).addConstraintViolation();
        }
        return isValid;
    }
    private Long getCurrentMemberId() {
        //TODO : SecurityContext에서 인증된 사용자 정보 가져오기
        return 1L;
    }

}
