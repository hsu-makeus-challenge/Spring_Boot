package umc.spring.service.UserMissionService;

import umc.spring.web.dto.userMission.UserMissionResponse;

public interface UserMissionCommandService {
    // 가게의 미션을 도전 중인 미션에 추가(미션 도전하기)
    UserMissionResponse.UserMissionResultDto saveUserMission(Long storeMissionId, Long userId);

    // 미션 성공 누르기

}
