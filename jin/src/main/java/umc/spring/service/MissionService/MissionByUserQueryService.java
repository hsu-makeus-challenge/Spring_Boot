package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.domain.User;


public interface MissionByUserQueryService {
    boolean isMissionAlreadyChallenged(User user, Mission mission);
}
