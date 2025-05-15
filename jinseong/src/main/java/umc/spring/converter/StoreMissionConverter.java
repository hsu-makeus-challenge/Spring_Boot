package umc.spring.converter;

import umc.spring.domain.mapping.StoreMission;
import umc.spring.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring.web.dto.MissionDTO.MissionResponseDTO;

import java.time.LocalDateTime;

public class StoreMissionConverter {
    public static MissionResponseDTO.CreateStoreMissionResultDTO toCreateStoreMissionResultDTO(StoreMission storeMission) {
        return MissionResponseDTO.CreateStoreMissionResultDTO.builder()
                .storeMissionId(storeMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StoreMission toStoreMission(MissionRequestDTO.CreateStoreMissionDTO request) {
        return StoreMission.builder()
                .deadline(request.getDeadline())
                .build()
                ;
    }
}
