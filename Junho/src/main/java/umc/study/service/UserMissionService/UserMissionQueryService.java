package umc.study.service.UserMissionService;

import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.UserMissionDto;

import java.util.List;


public interface UserMissionQueryService {
    List<UserMissionDto> findClearedMissions();
    List<UserMissionDto> findNotClearedMissions();
}
