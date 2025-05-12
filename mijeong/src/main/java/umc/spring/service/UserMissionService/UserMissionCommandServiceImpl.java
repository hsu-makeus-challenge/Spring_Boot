package umc.spring.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.converter.UserMissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;
import umc.spring.service.StoreMissionService.StoreMissionQueryService;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.web.dto.userMission.UserMissionResponse;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserMissionCommandServiceImpl implements UserMissionCommandService {

    private final UserMissionRepository userMissionRepository;
    private final UserMissionQueryService userMissionQueryService;
    private final StoreMissionQueryService storeMissionQueryService;
    private final UserQueryService userQueryService;

    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    @Transactional
    @Override
    public UserMissionResponse.UserMissionCreateResultDto saveUserMission(Long storeMissionId, Long userId) {
        StoreMission storeMission = storeMissionQueryService.validateStoreMission(storeMissionId);
        User user = userQueryService.validateUser(userId);

        // 유저 미션이 존재하는 지 검증
        if(userMissionQueryService.existsUserStoreMissionByStoreMissionIdAndUserId(storeMissionId, userId)) {
            throw new ErrorHandler(ErrorStatus.USER_MISSION_EXIST);
        }

        // 유저 미션 생성 및 미션, 유저와 연관관계 설정
        // 마감일 하드코딩
        UserMission userMission = UserMissionConverter.toUserMission(100);
        userMission.setStoreMission(storeMission);
        userMission.setUser(user);
        userMissionRepository.save(userMission);

        Long userMissionId = userMission.getId();
        log.info("유저미션 등록 완료, userMissionId: {}", userMissionId);

        return UserMissionConverter.toSUserMissionCreateResultDto(userMissionId);
    }

}
