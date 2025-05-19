package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.repository.MissionByUserRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.UserRepository;
import umc.spring.validation.annotation.NotAlreadyChallenged;

@Component
@RequiredArgsConstructor
public class NotAlreadyChallengedValidator implements ConstraintValidator<NotAlreadyChallenged, Long> {

    private final MissionByUserRepository missionByUserRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    @Override
    public void initialize(NotAlreadyChallenged constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {

        // 임의로 유저id 1L지정
        User user = userRepository.findById(1L).orElse(null);
        if(user == null) return true;

        //missionId 유효성 검사
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // 해당 유저, 미션 도전 기록이 있는지 확인
        // 존재 -> false 반환, 유효성 검증 실패
        return !missionByUserRepository.existsByUserAndMission(user, mission);
    }

}
