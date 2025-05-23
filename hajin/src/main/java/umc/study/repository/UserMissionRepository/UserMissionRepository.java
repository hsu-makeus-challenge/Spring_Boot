package umc.study.repository.UserMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.Users;
import umc.study.domain.mapping.UserMission;


public interface UserMissionRepository extends JpaRepository<UserMission, Integer> {
    boolean existsByUserIdAndMissionId(Integer userId, Long missionId);
    Page<UserMission> findAllByUser(Users user, PageRequest pageRequest);
}
