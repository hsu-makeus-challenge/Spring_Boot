package umc.spring.repository.StoreMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.StoreMission;

public interface StoreMissionRepository extends JpaRepository<StoreMission, Long> {
    Boolean existsByMissionIdAndStoreId(Long missionId, Long storeId);
}
