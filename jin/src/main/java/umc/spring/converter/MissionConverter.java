package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

public class MissionConverter {

    public static MissionResponseDTO.AddResultDTO toMissionResultDTO(Mission mission) {
        return MissionResponseDTO.AddResultDTO.builder()
                .missionId(mission.getMission())
                .createdAt(mission.getCreatedAt())
                .isReviewed(mission.getIsReviewed())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.AddMissionDto request, Store store) {
        return Mission.builder()
                .content(request.getContent())
                .deadline(request.getDeadline())
                .point(request.getPoint())
                .store(store)
                .isReviewed(false) // 생성시 기본값 설정
                .build();
    }
}
