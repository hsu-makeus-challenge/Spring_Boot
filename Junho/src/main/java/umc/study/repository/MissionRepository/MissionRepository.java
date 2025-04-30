package umc.study.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;
import umc.study.repository.UserMissionRepository.UserMissionRepositoryCustom;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}
