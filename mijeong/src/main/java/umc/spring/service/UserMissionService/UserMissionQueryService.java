package umc.spring.service.UserMissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.mission.MissionResponse;

public interface UserMissionQueryService {
    // 미션 상태(진행 중 or 완료)에 따른 유저의 미션 목록 조회
    Page<UserMission> findMissionsByStatus(Long userId, MissionStatus status, Integer pageNumber);

    // 홈 화면 - 선택된 지역에서 도전 가능한(아직 시작 전) 미션 목록 조회
    MissionResponse.HomeMissionPageDto findNotStartedMissionsByRegion(Long userId, Long regionId, MissionStatus status, Integer pageNumber);

    // 가게 미션 아이디와 유저 아이디를 통해 UserStoreMission 존재 여부 검증
    Boolean existsUserStoreMissionByStoreMissionIdAndUserId(Long storeMissionId, Long userId);
}
