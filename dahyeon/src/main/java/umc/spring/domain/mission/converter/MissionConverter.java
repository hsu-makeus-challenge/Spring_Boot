package umc.spring.domain.mission.converter;

import umc.spring.domain.mission.dto.MissionCreateRequestDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.store.entity.Store;

public class MissionConverter {
    public static Mission toMission(MissionCreateRequestDto request, Store store) {
        return Mission.builder()
                .description(request.getDescription())
                .rewardPoint(request.getRewardPoint())
                .minAmount(request.getMinAmount())
                .store(store)
                .build();
    }
}
