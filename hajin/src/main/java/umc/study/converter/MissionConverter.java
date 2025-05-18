package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionConverter {

    public static MissionResponseDTO.MissionResultDTO toDto(Mission mission){
        return MissionResponseDTO.MissionResultDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .title(mission.getTitle())
                .body(mission.getBody())
                .rewardPoint(mission.getRewardPoint())
                .created_at(mission.getCreatedAt())
                .updated_at(mission.getUpdatedAt())
                .build();
    }

    public static Mission toMission(Store store, MissionRequestDTO.MissionDto request) {
        return Mission.builder()
                .store(store)
                .title(request.getTitle())
                .body(request.getBody())
                .rewardPoint(request.getRewardPoint())
                //.startDate(request.getStartDate())
                //.endDate(request.getEndDate())
                .build();
    }
}
