package umc.spring.web.dto.userMission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class UserMissionResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "미션 도전하기 및 성공 누르기 응답 정보")
    public static class UserMissionResultDto {
        @Schema(description = "유저 미션 아이디", example = "1")
        Long userMissionId;

        @Schema(description = "생성 or 수전 날짜")
        LocalDateTime localDateTime;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "홈 화면 - 유저 홈 미션 목록 응답 정보")
    public static class HomeUserMissionInfoListDto {

        @Schema(description = "미션 목록")
        private List<HomeUserMissionInfoDto> missionList;

        @Schema(description = "총 페이지 수", example = "5")
        private Integer totalPage;

        @Schema(description = "총 미션 개수", example = "50")
        private Long totalElements;

        @Schema(description = "첫 페이지 여부", example = "true")
        private Boolean isFirst;

        @Schema(description = "마지막 페이지 여부", example = "false")
        private Boolean isLast;

        @Schema(description = "현재 페이지의 미션 수", example = "10")
        private Integer missionListSize;

        @Schema(description = "완료된 미션 수", example = "7")
        private Integer completeMissionCount;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "홈 화면 - 유저 미션 응답 정보")
    public static class HomeUserMissionInfoDto {

        @Schema(description = "유저 미션 ID", example = "1")
        private Long userMissionId;

        @Schema(description = "가게 이름", example = "김밥천국 강남점")
        private String storeName;

        @Schema(description = "음식 카테고리 목록", example = "[\"한식\", \"분식\"]")
        private List<String> foodCategoryList;

        @Schema(description = "미션 수행을 위한 최소 주문 금액", example = "15000")
        private Integer missionMoney;

        @Schema(description = "미션 성공 시 지급 포인트", example = "3000")
        private Integer reward;

        @Schema(description = "미션 상태", example = "IN_PROGRESS")
        private MissionStatus missionStatus;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "유저 홈 미션 목록 응답 정보")
    public static class UserMissionInfoListDto {

        @Schema(description = "미션 목록")
        private List<UserMissionInfoDto> missionList;

        @Schema(description = "총 페이지 수", example = "5")
        private Integer totalPage;

        @Schema(description = "총 미션 개수", example = "50")
        private Long totalElements;

        @Schema(description = "첫 페이지 여부", example = "true")
        private Boolean isFirst;

        @Schema(description = "마지막 페이지 여부", example = "false")
        private Boolean isLast;

        @Schema(description = "현재 페이지의 미션 수", example = "10")
        private Integer missionListSize;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "유저 미션 응답 정보")
    public static class UserMissionInfoDto {

        @Schema(description = "유저 미션 ID", example = "1")
        private Long userMissionId;

        @Schema(description = "가게 이름", example = "김밥천국 강남점")
        private String storeName;

        @Schema(description = "미션 수행을 위한 최소 주문 금액", example = "15000")
        private Integer missionMoney;

        @Schema(description = "미션 성공 시 지급 포인트", example = "3000")
        private Integer reward;

        @Schema(description = "미션 상태", example = "IN_PROGRESS")
        private MissionStatus missionStatus;
    }
}
