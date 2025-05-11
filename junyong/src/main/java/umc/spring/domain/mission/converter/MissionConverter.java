package umc.spring.domain.mission.converter;

import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.web.dto.MissionRequestDTO;
import umc.spring.domain.mission.web.dto.MissionResponseDTO;
import umc.spring.domain.region.data.Region;
import umc.spring.domain.store.data.Store;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.addDto request, Region region, Store store) {

        // String -> LocalDate 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deadLine = LocalDate.parse(request.getDeadLine(), formatter);

        return Mission.builder()
                .content(request.getContent())
                .reward(request.getReward())
                .deadline(deadLine)
                .store(store)
                .region(region)
                .build();
    }

    public static MissionResponseDTO.AddResultDto addResultDto(Mission mission) {
        return MissionResponseDTO.AddResultDto.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

}