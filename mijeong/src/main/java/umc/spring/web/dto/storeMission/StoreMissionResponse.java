package umc.spring.web.dto.storeMission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StoreMissionResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "가게에 미션 추가 응답 정보")
    public static class StoreMissionCreateResultDto {
        @Schema(description = "추가된 가게 미션 아이디", example = "1")
        Long storeMissionId;

        @Schema(description = "생성 날짜")
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "가게의 미션 목록 응답 정보")
    public static class StoreMissionInfoListDto {
        @Schema(description = "유저의 리뷰 목록")
        List<StoreMissionResponse.StoreMissionInfoDto> reviewList;

        @Schema(description = "현재 페이지의 리뷰 목록 개수", example = "10")
        Integer listSize;

        @Schema(description = "총 페이지 수", example = "8")
        Integer totalPage;

        @Schema(description = "총 리뷰 개수", example = "80")
        Long totalElements;

        @Schema(description = "페이지 처음 여부", example = "true")
        Boolean isFirst;

        @Schema(description = "페이지 마지막 여부", example = "false")
        Boolean isLast;

        @Schema(description = "가게 이름", example = "맛집이에요")
        String storeName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "가게의 미션 응답 정보")
    public static class StoreMissionInfoDto{
        @Schema(description = "가게 미션 아이디", example = "1")
        Long storeMissionId;

        @Schema(description = "기준 금액", example = "10000")
        Integer missionMoney;

        @Schema(description = "지급 포인트", example = "500")
        Integer reward;

        @Schema(description = "가게 미션 등록 날짜", example = "2025-05-16")
        LocalDate createdAt;
    }
}
