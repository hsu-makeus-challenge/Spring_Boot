package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.MissionByUser;


public interface MissionByUserQueryService {
    boolean isMissionAlreadyChallenged(User user, Mission mission);
    Page<MissionByUser> getMissionByUserList(Long userId, Integer page);
}
