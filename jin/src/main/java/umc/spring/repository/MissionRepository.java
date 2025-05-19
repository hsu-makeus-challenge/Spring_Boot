package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Mission;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m WHERE m.store.region.regionId = :regionId AND m.deadline >= :today")
    Page<Mission> findAvailableMissionsByRegionId(@Param("regionId") Long regionId,
                                                  @Param("today") LocalDate today,
                                                  Pageable pageable);

}
