package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.MissionRecord;
import umc.spring.domain.User;
import java.util.Optional;

@Repository
public interface MissionRecordRepository extends JpaRepository<MissionRecord,Long> {

    @Query("""
    SELECT mr.mission FROM MissionRecord mr
    WHERE mr.user.id = :userId
      AND mr.status = :status
    """)
    Page<Mission> getInProgressMissionsByUserId(@Param("userId") Long userId,
                                                @Param("status") MissionRecord.Status status,
                                                Pageable page);

    Page<Mission> findAllByUser(User user, PageRequest of);

    Optional<MissionRecord> findByUserId(Long userId);
}
