package umc.spring.service.MissionByUserService;

import umc.spring.domain.mapping.MissionByUser;

import java.util.List;

public interface MissionByUserService {

    List<MissionByUser> findMissionByUserIsCompleted(Long userId, Boolean isCompleted);

}
