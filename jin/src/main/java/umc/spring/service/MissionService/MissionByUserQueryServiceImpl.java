package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.repository.MissionByUserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionByUserQueryServiceImpl implements MissionByUserQueryService{

    private final MissionByUserRepository missionByUserRepository;

    @Override
    public boolean isMissionAlreadyChallenged(User user, Mission mission) {
        return missionByUserRepository.existsByUserAndMission(user, mission);
    }
}
