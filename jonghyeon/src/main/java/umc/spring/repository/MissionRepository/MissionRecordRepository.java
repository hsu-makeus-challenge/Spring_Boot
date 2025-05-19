package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.MissionRecord;
import umc.spring.domain.User;
import java.util.Optional;

@Repository
public interface MissionRecordRepository extends JpaRepository<MissionRecord,Long> {


    Page<MissionRecord> findAllByUser(User user, PageRequest of);

    Optional<MissionRecord> findByUserId(Long userId);
}
