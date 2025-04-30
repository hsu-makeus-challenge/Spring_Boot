package umc.spring.repository.MissionByUserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.MissionByUser;

public interface MissionByUserRepository extends JpaRepository<MissionByUser, Long>, MissionByUserRepositoryCustom {
}
