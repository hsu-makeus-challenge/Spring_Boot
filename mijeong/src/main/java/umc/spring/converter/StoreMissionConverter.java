package umc.spring.converter;

import umc.spring.domain.mapping.StoreMission;
import umc.spring.web.dto.storeMission.StoreMissionResponse;

import java.time.LocalDateTime;

public class StoreMissionConverter {

    // createDto -> StoreMission Entity
    public static StoreMission toStoreMission() {
        return StoreMission.builder()
                .build();
    }

    public static StoreMissionResponse.StoreMissionCreateResultDto toStoreMissionCreateResultDto(Long storeMissionId) {
        return StoreMissionResponse.StoreMissionCreateResultDto.builder()
                .storeMissionId(storeMissionId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
