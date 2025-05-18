package umc.spring.domain.member.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.web.dto.MemberResponseDTO;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.domain.mission.web.dto.MissionResponseDTO;
import umc.spring.global.common.converter.PageConverter;

import java.util.List;
import java.util.Random;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {

        Random random = new Random();
        int confirmNumber = random.nextInt(999999);

        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .confirmNumber(String.valueOf(confirmNumber)) // 임의값
                .build();
    }

    public static MissionResponseDTO.ChallengeResultDto toChallengeResultDto(MemberMission memberMission) {
        return MissionResponseDTO.ChallengeResultDto.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .memberId(memberMission.getMember().getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.MissionDto toMissionDto(MemberMission memberMission) {

        Mission mission = memberMission.getMission();

        return MemberResponseDTO.MissionDto.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionContent(mission.getContent())
                .missionReward(mission.getReward())
                .deadLine(mission.getDeadline())
                .status(memberMission.getStatus().getStatus())
                .build();
    }

    public static MemberResponseDTO.MissionListDto toMissionListDto(Page<MemberMission> memberMissionList) {
        List<MemberResponseDTO.MissionDto> missionDtoList = memberMissionList.stream()
                .map(MemberMissionConverter::toMissionDto)
                .toList();

        return MemberResponseDTO.MissionListDto.builder()
                .missionList(missionDtoList)
                .pageInfo(PageConverter.pageToListPageDto(memberMissionList))
                .build();
    }

}
