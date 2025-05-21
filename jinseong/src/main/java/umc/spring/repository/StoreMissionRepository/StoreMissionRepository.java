package umc.spring.repository.StoreMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.StoreMission;

import java.util.List;

public interface StoreMissionRepository extends JpaRepository<StoreMission, Long>, StoreMissionRepositoryCustom {
    // 1. 먼저 ID만 페이징해서 조회
    @Query("SELECT sm.id FROM StoreMission sm WHERE sm.store = :store")
    Page<Long> findStoreMissionIdsByStore(@Param("store")Store store, Pageable pageable);

    // 2. 조회된 ID로 페치 조인 수행
    @Query("SELECT sm FROM StoreMission sm LEFT JOIN FETCH sm.mission WHERE sm.id IN :ids")
    List<StoreMission> findStoreMissionsByIdWithMissions(@Param("ids")List<Long> storeMissionIds);
}
