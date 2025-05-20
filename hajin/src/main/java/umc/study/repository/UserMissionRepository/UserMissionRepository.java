package umc.study.repository.UserMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.mapping.UserMission;


public interface UserMissionRepository extends JpaRepository<UserMission, Integer> {
    boolean existsByUserIdAndMissionId(Integer userId, Long missionId);
}
