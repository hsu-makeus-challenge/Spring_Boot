package umc.spring.web.dto.mission;

import lombok.*;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public class MissionResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class HomeMissionListDto{
        Integer totalPage; // 총 페이지 개수
        Long totalElements; // 총 데이터 개수
        Boolean isFirst; // 페이지 처음 여부
        Boolean isLast; // 페이지 마지막 여부
        List<MissionInfoDto> missionList;
        Integer missionListSize; // 현재 페이지의 미션 개수
        Integer completeMissionCount; // 완료한 미션의 개수 (성공인 미션)
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionInfoDto {
        Long missionId; // 유저 가게 미션 아이디
        String storeName; // 가게 이름
        List<String> foodCategoryList; // 가게의 음식 카테고리
        Integer missionMoney; // 기준 금액
        Integer reward; // 지급 포인트
        MissionStatus missionStatus; // 미션 상태 (성공, 진행 중, 실패 등)
    }
}
