package umc.spring.repository.UserMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.mission.MissionResponse;

public interface UserMissionRepositoryCustom {
    // 미션 상태에 따른 미션 목록 조회
    Page<UserMission> findUserMissionsByStatus(Long userId, MissionStatus status, Pageable pageable);

    // 선택된 지역에서 도전 가능한 미션 조회
    Page<UserMission> findUserMissionsByRegionAndStatus(Long userId, Long regionId, MissionStatus status, Pageable pageable);

    // 최신 10개의 유저 미션 데이터 중 성공인 미션만 count
    Long getSucceededMissionCount(Long userId, Long regionId);
}
