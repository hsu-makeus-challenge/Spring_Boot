package umc.spring.converter;

import umc.spring.domain.mapping.MissionByUser;
import umc.spring.web.dto.mission.MissionResponseDTO;

public class MissionByUserConverter {

    public static MissionResponseDTO.ChallengeResultDTO toChallengeResultDTO(MissionByUser missionByUser) {
        return MissionResponseDTO.ChallengeResultDTO.builder()
                .missionId(missionByUser.getMission().getId())
                .userId(missionByUser.getUser().getId())
                .createdAt(missionByUser.getCreatedAt())
                .build();
    }
}
