package umc.spring.domain.mission.validation.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.member.service.MemberMissionQueryService;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.repository.MissionRepository;
import umc.spring.domain.mission.validation.annotation.MemberMissionExist;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.MissionHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberMissionExistValidator implements ConstraintValidator<MemberMissionExist, Long> {

    private final MemberMissionQueryService memberMissionQueryService;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    public void initialize(MemberMissionExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {

        /*
        요청 헤더로 유저 정보를 가져올 때...
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = authHeader.substring(7);
        String userId;

        try {
            // 대충 토큰으로 userId 찾는 코드
        }catch (Exception e) {
            return false;
        }
        */

        Member member = memberRepository.findById(1L).orElse(null); // 임의로 아무 멤버 가져옴
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        boolean isValid = memberMissionQueryService.isExistByMemberAndMission(member, mission);

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_CHALLENGING.toString()).addConstraintViolation();
        }

        return isValid;
    }

}
