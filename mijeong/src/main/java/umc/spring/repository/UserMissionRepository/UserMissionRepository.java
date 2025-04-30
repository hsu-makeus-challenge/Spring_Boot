package umc.spring.repository.UserMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Store;

public interface UserMissionRepository extends JpaRepository<Store, Long>, UserMissionRepositoryCustom {
}
