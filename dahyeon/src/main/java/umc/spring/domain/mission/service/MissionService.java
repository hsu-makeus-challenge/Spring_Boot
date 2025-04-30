package umc.spring.domain.mission.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import umc.spring.domain.mission.dto.MissionResponseDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.entity.enums.MissionStatus;

public interface MissionService {
  Page<Mission> findAvailableMissionsByLocation(
      Float latitude, Float longitude, Double radius, Pageable pageable);

  Page<MissionResponseDto.MemberMissionResponseDto> getMemberMissions(
      Long memberId, MissionStatus status, Pageable pageable);
}
