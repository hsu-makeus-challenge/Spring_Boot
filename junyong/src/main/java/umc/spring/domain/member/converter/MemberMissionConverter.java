package umc.spring.domain.member.converter;

import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.domain.mission.web.dto.MissionResponseDTO;

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

}
