package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.MissionRecord;
import umc.spring.domain.User;
import umc.spring.web.dto.MissionRecordRequestDTO;
import umc.spring.web.dto.MissionRecordResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissionRecordConverter {


    public static MissionRecordResponseDTO.MissionRecordResultDTO toaddMissionRecordResultDTO(MissionRecord missionRecord){
        return MissionRecordResponseDTO.MissionRecordResultDTO.builder()
                .MissionRecordId(missionRecord.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionRecord toMissionRecord(
            MissionRecordRequestDTO.addMissionRecordResultDTO request,
            User user,
            Mission mission) {

        return MissionRecord.builder()
                .user(user)
                .mission(mission)
                .status(request.getStatus())
                .pointRecordList(new ArrayList<>())
                .build();
    }

    public static MissionRecordResponseDTO.MissionRecordUpdateDTO toMissionRecordUpdateDTO(MissionRecord missionRecord) {
        return MissionRecordResponseDTO.MissionRecordUpdateDTO.builder()
                .MissionRecordId(missionRecord.getId())
                .status(missionRecord.getStatus())
                .updatedAt(missionRecord.getUpdatedAt())
                .build();
    }
}
