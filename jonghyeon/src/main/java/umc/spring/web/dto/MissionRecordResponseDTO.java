package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.Mission;
import umc.spring.domain.MissionRecord;

import java.time.LocalDateTime;

public class MissionRecordResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionRecordResultDTO {
        Long MissionRecordId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionRecordUpdateDTO {
        Long MissionRecordId;
        MissionRecord.Status status;
        LocalDateTime updatedAt;
    }


//    @Builder
//    @Getter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class MissionRecordDTO{
//        Mission mission;
//
//    }
//
//    @Builder
//    @Getter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class MissionRecordListDTO{
//
//    }

}
