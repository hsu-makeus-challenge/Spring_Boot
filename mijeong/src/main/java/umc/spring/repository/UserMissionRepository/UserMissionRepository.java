package umc.spring.repository.UserMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long>, UserMissionRepositoryCustom {
    Boolean existsByStoreMissionIdAndUserId(Long storeMissionId, Long userId);
}
