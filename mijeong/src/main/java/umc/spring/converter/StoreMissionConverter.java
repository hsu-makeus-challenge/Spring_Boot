package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.web.dto.storeMission.StoreMissionResponse;

import java.time.LocalDateTime;
import java.util.List;

public class StoreMissionConverter {

    // createDto -> StoreMission Entity
    public static StoreMission toStoreMission() {
        return StoreMission.builder()
                .build();
    }

    public static StoreMissionResponse.StoreMissionCreateResultDto toStoreMissionCreateResultDto(Long storeMissionId) {
        return StoreMissionResponse.StoreMissionCreateResultDto.builder()
                .storeMissionId(storeMissionId)
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 가게의 미션 조회 관련 컨버터
    public static StoreMissionResponse.StoreMissionInfoDto toStoreMissionInfoDto(StoreMission storeMission){
        return StoreMissionResponse.StoreMissionInfoDto.builder()
                .storeMissionId(storeMission.getId())
                .missionMoney(storeMission.getMission().getMissionMoney())
                .reward(storeMission.getMission().getReward())
                .createdAt(storeMission.getCreatedAt().toLocalDate())
                .build();
    }

    // 가게의 미션 목록 조회 관련 컨버터
    public static StoreMissionResponse.StoreMissionInfoListDto toStoreMissionInfoListDto(Page<StoreMission> storeMissionPage, String storeName) {
        // StoreMission 리스트를 StoreMissionInfoDto 리스트로 변환
        List<StoreMissionResponse.StoreMissionInfoDto> storeMissionList = storeMissionPage.stream()
                .map(StoreMissionConverter::toStoreMissionInfoDto)
                .toList();

        return StoreMissionResponse.StoreMissionInfoListDto.builder()
                .isFirst(storeMissionPage.isFirst())
                .isLast(storeMissionPage.isLast())
                .totalPage(storeMissionPage.getTotalPages())
                .totalElements(storeMissionPage.getTotalElements())
                .listSize(storeMissionList.size())
                .reviewList(storeMissionList)
                .storeName(storeName)
                .build();
    }
}
