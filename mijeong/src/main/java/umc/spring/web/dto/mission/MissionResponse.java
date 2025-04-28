package umc.spring.web.dto.mission;

import lombok.*;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public class MissionResponse {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class HomeMissionPageDto {
        private Integer totalPage; // 총 페이지 개수
        private Long totalElements; // 총 데이터 개수
        private Boolean isFirst; // 페이지 처음 여부
        private Boolean isLast; // 페이지 마지막 여부
        private List<MissionInfoDto> missionList;
        private Integer missionListSize; // 현재 페이지의 미션 개수
        private Integer completeMissionCount; // 완료한 미션의 개수 (성공인 미션)
    }

    @Builder
    @Getter
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
