package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.mission.MissionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    // 홈 화면 - 도전 가능한 미션 리스트 변환
    public static MissionResponse.HomeMissionListDto toHomeMissionListDto(Page<UserMission> userMissionPage, Long succeedCount) {
        List<MissionResponse.MissionInfoDto> dtoList = userMissionPage.getContent().stream()
                .map(MissionConverter::toMissionInfoDto)
                .collect(Collectors.toList());

        return MissionResponse.HomeMissionListDto.builder()
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .isFirst(userMissionPage.isFirst())
                .isLast(userMissionPage.isLast())
                .missionList(dtoList)
                .missionListSize(userMissionPage.getNumberOfElements())
                .completeMissionCount(succeedCount != null ? succeedCount.intValue() : 0)
                .build();
    }

    // 홈 화면 - 도전 가능한 각각의 미션 정보
    public static MissionResponse.MissionInfoDto toMissionInfoDto(UserMission userMission) {
        return MissionResponse.MissionInfoDto.builder()
                .missionId(userMission.getId())
                .storeName(userMission.getStoreMission().getStore().getStoreName())
                .foodCategoryList(
                        userMission.getStoreMission().getStore()
                                .getStoreFoodCategoryList().stream()
                                .map(sfc -> sfc.getFoodCategory().getFoodCategoryName())
                                .distinct() // 중복 제거
                                .collect(Collectors.toList())
                )
                .missionMoney(userMission.getStoreMission().getMission().getMissionMoney())
                .reward(userMission.getStoreMission().getMission().getReward())
                .missionStatus(userMission.getMissionStatus())
                .build();
    }
}
