package umc.spring.converter;


import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissionConveter {

    public static MissionResponseDTO.addMissionResultDTO toAddMissionResultDTO(Mission mission){
        return MissionResponseDTO.addMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.addMissionDTO request, Store store) {
        return Mission.builder()
                .store(store)
                .content(request.getContent())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionRecordList(new ArrayList<>())
                .build();
    }

}
