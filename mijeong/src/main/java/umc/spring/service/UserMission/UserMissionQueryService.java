package umc.spring.service.UserMission;

import org.springframework.data.domain.Page;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;

public interface UserMissionQueryService {
    // 홈 화면 - 미션 상태(진행 중 or 완료)에 따른 유저의 미션 목록 조회
    Page<UserMission> findMissionsByStatus(Long userId, MissionStatus status, Integer pageNumber);
}
