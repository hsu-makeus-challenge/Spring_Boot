package umc.study.validation.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.validation.annotation.AlreadyJoinedMission;

// 새로운 검증기
@Component
@RequiredArgsConstructor
public class AlreadyJoinedMissionValidator implements ConstraintValidator<AlreadyJoinedMission, Long> {

    private final UserMissionRepository userMissionRepository;
    private String userIdParameterName;

    @Override
    public void initialize(AlreadyJoinedMission constraintAnnotation) {
        this.userIdParameterName = constraintAnnotation.userIdParameterName();
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Integer userId = Integer.parseInt(request.getAttribute(userIdParameterName).toString()); // RequestContext에서 userId 가져오기 (주의: Interceptor 설정 필요)

        boolean alreadyJoined = userMissionRepository.existsByUserIdAndMissionId(userId, missionId);
        if (alreadyJoined) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 해당 미션을 등록한 사용자입니다.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}