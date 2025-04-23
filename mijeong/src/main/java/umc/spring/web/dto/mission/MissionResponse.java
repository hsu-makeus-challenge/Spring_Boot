package umc.spring.web.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

public class MissionResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeMissionStatusDto {
        Long missionId; // 유저 가게 미션 아이디
        String storeName; // 가게 이름
        Integer missionMoney; // 기준 금액
        Integer missionPoint; // 지급 포인트
        MissionStatus missionStatus; // 미션 상태 (성공, 진행 중, 실패 등)
    }
}
