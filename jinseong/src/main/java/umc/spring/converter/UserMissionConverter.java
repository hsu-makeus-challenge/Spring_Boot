package umc.spring.converter;

import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.MissionDTO.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class UserMissionConverter {
    public static UserMission toUserMission() {
        return UserMission.builder()
                .code(generateVariableLengthNumericCode(6, 10))
                .build()
                ;
    }

    public static String generateVariableLengthNumericCode(int minLength, int maxLength) {
        int length = ThreadLocalRandom.current().nextInt(minLength, maxLength + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ThreadLocalRandom.current().nextInt(10));
        }
        return sb.toString();
    }

    public static MissionResponseDTO.CreateUserMissionResultDTO toCreateUserMissionResultDTO(UserMission userMission) {
        return MissionResponseDTO.CreateUserMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.CompleteUserMissionResultDTO completeUserMissionResultDTO(UserMission userMission) {
        return MissionResponseDTO.CompleteUserMissionResultDTO.builder()
                .userMissionId(userMission.getId())
                .status(userMission.getStatus())
                .updatedAt(userMission.getUpdatedAt().toLocalDate())
                .build()
                ;
    }
}
