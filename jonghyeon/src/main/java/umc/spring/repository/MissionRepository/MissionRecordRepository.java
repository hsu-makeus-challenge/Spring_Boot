package umc.spring.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.MissionRecord;

@Repository
public interface MissionRecordRepository extends JpaRepository<MissionRecord,Long> {


}
