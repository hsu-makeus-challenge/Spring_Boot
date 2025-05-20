package umc.study.service.UserMissionService;

import org.springframework.data.domain.Page;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.UserMissionDto;

import java.util.List;


public interface UserMissionQueryService {
    List<UserMissionDto> findClearedMissions();
    List<UserMissionDto> findNotClearedMissions();
    Page<UserMission> getMyClearedMissions(Integer page);
    void clearMission(Long userMissionId);
}
