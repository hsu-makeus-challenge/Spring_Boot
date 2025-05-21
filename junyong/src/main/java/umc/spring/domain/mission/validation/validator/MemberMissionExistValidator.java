package umc.spring.domain.mission.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.repository.memberMission.MemberMissionRepository;
import umc.spring.domain.mission.validation.annotation.ExistMemberMission;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;

@Component
@RequiredArgsConstructor
public class MemberMissionExistValidator implements ConstraintValidator<ExistMemberMission, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Long memberMissionId, ConstraintValidatorContext context) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId).orElse(null);
        boolean isValid = memberMission != null;

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_CHALLENGING.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
