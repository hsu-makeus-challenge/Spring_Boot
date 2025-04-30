package umc.spring.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import umc.spring.domain.mission.entity.Mission;

public interface MissionRepositoryCustom {
  Page<Mission> findAvailableMissionsByLocation(
      Float latitude, Float longitude, Double radius, Pageable pageable);
}
