package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.Mission;
import umc.spring.domain.MissionRecord;
import umc.spring.domain.PointRecord;
import umc.spring.domain.User;

import java.util.List;

public class MissionRecordRequestDTO {

    @Getter
    public static class addMissionRecordResultDTO {

        Long missionId;
        MissionRecord.Status status;
        List<PointRecord> pointRecordList;
    }
}
