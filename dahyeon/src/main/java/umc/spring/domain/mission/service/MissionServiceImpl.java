package umc.spring.domain.mission.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.exception.MemberHandler;
import umc.spring.domain.member.exception.status.MemberErrorStatus;
import umc.spring.domain.member.repository.MemberRepository;
import umc.spring.domain.mission.converter.MissionConverter;
import umc.spring.domain.mission.dto.MissionCreateRequestDto;
import umc.spring.domain.mission.dto.MissionResponseDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.entity.enums.MissionStatus;
import umc.spring.domain.mission.entity.mappings.MemberMission;
import umc.spring.domain.mission.exception.MissionHandler;
import umc.spring.domain.mission.exception.status.MissionErrorStatus;
import umc.spring.domain.mission.repository.MemberMissionRepository;
import umc.spring.domain.mission.repository.MissionRepository;
import umc.spring.domain.store.entity.Store;
import umc.spring.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

  private final MissionRepository missionRepository;
  private final MemberRepository memberRepository;
  private final MemberMissionRepository memberMissionRepository;
  private final StoreRepository storeRepository;

  @Override
  public Page<Mission> findAvailableMissionsByLocation(
      Float latitude, Float longitude, Double radius, Pageable pageable) {
    return missionRepository.findAvailableMissionsByLocation(latitude, longitude, radius, pageable);
  }

  @Override
  public Page<MissionResponseDto.MemberMissionResponseDto> getMemberMissions(
      Long memberId, MissionStatus status, Pageable pageable) {
    memberRepository
        .findById(memberId)
        .orElseThrow(() -> new MemberHandler(MemberErrorStatus.MEMBER_NOT_FOUND));

    return memberMissionRepository
        .findAllByMemberIdAndStatus(memberId, status, pageable)
        .map(MissionResponseDto.MemberMissionResponseDto::from);
  }

  @Override
  @Transactional
  public Mission createMission(Long storeId, MissionCreateRequestDto request) {
    Store store = storeRepository.findById(storeId).get();
    Mission mission = MissionConverter.toMission(request, store);
    store.getMissions().add(mission);
    return missionRepository.save(mission);
  }

  @Override
  @Transactional
  public MemberMission createChallenge(Long missionId, Long memberId) {
    Mission mission = missionRepository.findById(missionId).get();
    Member member = memberRepository.findById(memberId).get();
    MemberMission memberMission = MemberMission.startChallenge(member, mission);

    member.getMemberMissions().add(memberMission);
    mission.getMemberMissions().add(memberMission);
    return memberMissionRepository.save(memberMission);
  }

  @Override
  public boolean checkMissionChallenge(Long memberId, Long missionId) {
    Mission mission =
        missionRepository
            .findById(missionId)
            .orElseThrow(() -> new MissionHandler(MissionErrorStatus.MISSION_NOT_FOUND));
    boolean isAlreadyChallenging =
        memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
            memberId, missionId, MissionStatus.PROGRESS);
    // true이면 "도전 가능"한 상태 → 유효성 검증 통과
    return !isAlreadyChallenging;
  }
}
