package umc.spring.repository.UserMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;

public interface UserMissionRepositoryCustom {
    // 홈 화면 - 미션 상태에 따른 미션 목록 조회
    Page<UserMission> findUserMissionsByStatus(Long userId, MissionStatus status, Pageable pageable);
}
