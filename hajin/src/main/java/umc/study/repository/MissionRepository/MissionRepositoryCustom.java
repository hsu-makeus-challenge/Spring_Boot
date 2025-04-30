package umc.study.repository.MissionRepository;

import umc.study.domain.Mission;
import umc.study.domain.mapping.UserMission;

import java.util.List;

public interface MissionRepositoryCustom {
    List<UserMission> dynamicQueryWithBooleanBuilder(Long userId);
    List<Mission> missionsByRegion(Long regionId);
}
