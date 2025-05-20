package umc.spring.domain.mission.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import umc.spring.domain.mission.dto.MissionCreateRequestDto;
import umc.spring.domain.mission.dto.MissionCreateResponseDto;
import umc.spring.domain.mission.dto.MissionListResponseDto;
import umc.spring.domain.mission.dto.MissionResponseDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.entity.mappings.MemberMission;
import umc.spring.domain.store.entity.Store;

public class MissionConverter {
  public static Mission toMission(MissionCreateRequestDto request, Store store) {
    return Mission.builder()
        .description(request.getDescription())
        .rewardPoint(request.getRewardPoint())
        .minAmount(request.getMinAmount())
        .store(store)
        .build();
  }

  public static MissionListResponseDto.MissionsPreViewListDto toMissionListResponseDto(
      Page<Mission> missionList) {
    List<MissionCreateResponseDto> missionResponseDtos =
        missionList.getContent().stream()
            .map(MissionCreateResponseDto::from)
            .collect(Collectors.toList());

    return MissionListResponseDto.MissionsPreViewListDto.builder()
        .missinoList(missionResponseDtos)
        .listSize(missionList.getNumberOfElements())
        .totalPage(missionList.getTotalPages())
        .totalElements(missionList.getTotalElements())
        .isFirst(missionList.isFirst())
        .isLast(missionList.isLast())
        .build();
  }

  public static MissionListResponseDto.MemberMissionsPreViewListDto toMemberMissionListResponseDto(
      Page<MemberMission> missionList) {
    List<MissionResponseDto.MemberMissionResponseDto> missionResponseDtos =
        missionList.getContent().stream()
            .map(MissionResponseDto.MemberMissionResponseDto::from)
            .collect(Collectors.toList());

    return MissionListResponseDto.MemberMissionsPreViewListDto.builder()
        .missinoList(missionResponseDtos)
        .listSize(missionList.getNumberOfElements())
        .totalPage(missionList.getTotalPages())
        .totalElements(missionList.getTotalElements())
        .isFirst(missionList.isFirst())
        .isLast(missionList.isLast())
        .build();
  }
}
