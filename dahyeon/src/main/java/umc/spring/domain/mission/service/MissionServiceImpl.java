package umc.spring.domain.mission.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.spring.domain.mission.dto.MissionResponseDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.entity.enums.MissionStatus;
import umc.spring.domain.mission.repository.MemberMissionRepository;
import umc.spring.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

  private final MissionRepository missionRepository;
  private final MemberMissionRepository memberMissionRepository;

  @Override
  public Page<Mission> findAvailableMissionsByLocation(
      Float latitude, Float longitude, Double radius, Pageable pageable) {
    return missionRepository.findAvailableMissionsByLocation(latitude, longitude, radius, pageable);
  }

  @Override
  public Page<MissionResponseDto.MemberMissionResponseDto> getMemberMissions(
      Long memberId, MissionStatus status, Pageable pageable) {
    // TODO: 회원 존재 여부 검증 필요

    return memberMissionRepository
        .findAllByMemberIdAndStatus(memberId, status, pageable)
        .map(MissionResponseDto.MemberMissionResponseDto::from);
  }
}
