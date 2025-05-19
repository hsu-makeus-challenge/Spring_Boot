package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.domain.Users;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.UserMissionRequestDTO;
import umc.study.web.dto.UserMissionResponseDTO;

import java.time.LocalDateTime;

public class UserMissionConverter {
    public static UserMissionResponseDTO.UserMissionResultDto resultDTO(UserMission userMission) {
        return UserMissionResponseDTO.UserMissionResultDto.builder()
                .userId(userMission.getUser().getId())
                .missionId(userMission.getMission().getId())
                .missionStatus(userMission.getMissionStatus())
                .deadLine(LocalDateTime.now().plusDays(7))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static UserMission toUserMission(Users user, Mission mission, UserMissionRequestDTO.UserMissionDto request) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .missionStatus(request.getMissionStatus())
                .confirmNumber(0)
                .build();
    }
}