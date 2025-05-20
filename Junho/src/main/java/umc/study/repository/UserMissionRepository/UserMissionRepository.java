package umc.study.repository.UserMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
public interface UserMissionRepository extends JpaRepository<UserMission, Long>, UserMissionRepositoryCustom {
    Page<UserMission> findAllByUserAndIsClearedTrue(User user, PageRequest pageRequest);
}