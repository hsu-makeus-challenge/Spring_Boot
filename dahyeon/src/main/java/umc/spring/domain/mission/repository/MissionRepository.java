package umc.spring.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import umc.spring.domain.mission.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {

  Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

  Slice<Mission> findSliceByStore_Id(Long storeId, Pageable pageable);
}
