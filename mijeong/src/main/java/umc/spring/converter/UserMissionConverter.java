package umc.spring.converter;


import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.userMission.UserMissionResponse;

import java.time.LocalDateTime;

public class UserMissionConverter {

    // createDto -> UserMission Entity
    public static UserMission toUserMission(Integer day) {
        return UserMission.builder()
                .deadline(LocalDateTime.now().plusDays(day))
                .build();
    }

    public static UserMissionResponse.UserMissionCreateResultDto toSUserMissionCreateResultDto(Long userMissionId) {
        return UserMissionResponse.UserMissionCreateResultDto.builder()
                .userMissionId(userMissionId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
