package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.validation.annotation.ExistMission;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {

    private final UserMissionRepository userMissionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        // 사용자 ID와 미션 ID로 미션이 존재하는지 확인
        boolean exists = userMissionRepository.existsByUserIdAndMissionId(getUserIdFromContext(), missionId);
        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("해당 사용자는 이미 이 미션을 등록했습니다.")
                    .addConstraintViolation();
            return false;

        }
        return true;
    }

    // 사용자 ID를 RequestContext에서 가져오기
    private Integer getUserIdFromContext() {
        return (Integer) RequestContextHolder.currentRequestAttributes()
                .getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
    }
}
