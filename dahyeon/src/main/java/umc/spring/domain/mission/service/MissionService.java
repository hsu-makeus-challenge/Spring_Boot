package umc.spring.domain.mission.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import umc.spring.domain.mission.dto.MissionCreateRequestDto;
import umc.spring.domain.mission.dto.MissionResponseDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.entity.enums.MissionStatus;
import umc.spring.domain.mission.entity.mappings.MemberMission;

public interface MissionService {
  Page<Mission> findAvailableMissionsByLocation(
      Float latitude, Float longitude, Double radius, Pageable pageable);

  Page<MissionResponseDto.MemberMissionResponseDto> getMemberMissions(
      Long memberId, MissionStatus status, Pageable pageable);

  public Mission createMission(Long storeId, MissionCreateRequestDto dto);

  MemberMission createChallenge(Long missionId, Long memberId);

  boolean checkMissionChallenge(Long memberId, Long missionId);

  Page<Mission> getMissionList(Long memberId, Long storeId, Integer page);

  Page<MemberMission> getMyMissions(Long memberId, Integer page, MissionStatus status);
}
