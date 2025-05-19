package umc.spring.converter;


import org.springframework.data.domain.Page;
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

    public static MissionResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.storeMissionListDTO toMissionListDTO(Page<Mission> missionList) {
        return MissionResponseDTO.storeMissionListDTO.builder()
                .storeMissionList(missionList.getContent().stream()
                        .map(MissionConveter::toMissionDTO)
                        .toList())
                .listSize(missionList.getSize())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }

}
