package umc.spring.converter;


import org.springframework.data.domain.Page;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.userMission.UserMissionResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserMissionConverter {

    // createDto -> UserMission Entity
    public static UserMission toUserMission(Integer day) {
        return UserMission.builder()
                .deadline(LocalDateTime.now().plusDays(day))
                .build();
    }

    public static UserMissionResponse.UserMissionResultDto toSUserMissionResultDto(UserMission userMission, MissionStatus status) {
        return UserMissionResponse.UserMissionResultDto.builder()
                .userMissionId(userMission.getId())
                .localDateTime(status.equals(MissionStatus.CHALLENGE) ? userMission.getCreatedAt() : userMission.getUpdatedAt())
                .build();
    }

    // 홈 화면 - 도전 가능한 미션 리스트 변환
    public static UserMissionResponse.HomeUserMissionInfoListDto toHomeUserMissionListDto(Page<UserMission> userMissionPage, Long succeedCount) {
        List<UserMissionResponse.HomeUserMissionInfoDto> userMissionList = userMissionPage.getContent().stream()
                .map(UserMissionConverter::toHomeUserMissionInfoDto)
                .collect(Collectors.toList());

        return UserMissionResponse.HomeUserMissionInfoListDto.builder()
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .isFirst(userMissionPage.isFirst())
                .isLast(userMissionPage.isLast())
                .missionList(userMissionList)
                .missionListSize(userMissionPage.getNumberOfElements())
                .completeMissionCount(succeedCount != null ? succeedCount.intValue() : 0)
                .build();
    }

    // 홈 화면 - 도전 가능한 각각의 미션 정보
    public static UserMissionResponse.HomeUserMissionInfoDto toHomeUserMissionInfoDto(UserMission userMission) {
        return UserMissionResponse.HomeUserMissionInfoDto.builder()
                .userMissionId(userMission.getId())
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


    // 진행중인 유저 미션 리스트 변환
    public static UserMissionResponse.UserMissionInfoListDto toUserMissionInfoListDto(Page<UserMission> userMissionPage) {
        List<UserMissionResponse.UserMissionInfoDto> userMissionList = userMissionPage.getContent().stream()
                .map(UserMissionConverter::toUserMissionInfoDto)
                .collect(Collectors.toList());

        return UserMissionResponse.UserMissionInfoListDto.builder()
                .totalPage(userMissionPage.getTotalPages())
                .totalElements(userMissionPage.getTotalElements())
                .isFirst(userMissionPage.isFirst())
                .isLast(userMissionPage.isLast())
                .missionList(userMissionList)
                .missionListSize(userMissionPage.getNumberOfElements())
                .build();
    }

    // 진행중인 유저 미션 정보 변환
    public static UserMissionResponse.UserMissionInfoDto toUserMissionInfoDto(UserMission userMission) {
        return UserMissionResponse.UserMissionInfoDto.builder()
                .userMissionId(userMission.getId())
                .storeName(userMission.getStoreMission().getStore().getStoreName())
                .missionMoney(userMission.getStoreMission().getMission().getMissionMoney())
                .reward(userMission.getStoreMission().getMission().getReward())
                .missionStatus(userMission.getMissionStatus())
                .build();
    }
}
