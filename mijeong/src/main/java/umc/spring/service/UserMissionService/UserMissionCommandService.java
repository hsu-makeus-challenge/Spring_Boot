package umc.spring.service.UserMissionService;

import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.userMission.UserMissionResponse;

public interface UserMissionCommandService {
    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    UserMissionResponse.UserMissionResultDto saveUserMission( Long userId, Long storeMissionId);

    // 미션 성공 누르기
    UserMissionResponse.UserMissionResultDto updateUserMissionStatus(Long userId, Long userMissionId,MissionStatus status);
}
