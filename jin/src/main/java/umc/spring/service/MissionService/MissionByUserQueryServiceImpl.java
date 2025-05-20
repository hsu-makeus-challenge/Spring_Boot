package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.UserIdHandler;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.MissionByUser;
import umc.spring.repository.MissionByUserRepository;
import umc.spring.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionByUserQueryServiceImpl implements MissionByUserQueryService{

    private final MissionByUserRepository missionByUserRepository;
    private final UserRepository userRepository;

    @Override
    public boolean isMissionAlreadyChallenged(User user, Mission mission) {
        return missionByUserRepository.existsByUserAndMission(user, mission);
    }

    @Override
    public Page<MissionByUser> getMissionByUserList(Long userId, Integer page){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserIdHandler(ErrorStatus.USER_NOT_FOUND));

        // 진행중 -> 미완료라고 생각하고 처리
        Page<MissionByUser> UserMissionPage = missionByUserRepository.findAllByUserAndIsCompleted(user, false, PageRequest.of(page, 10));
        return UserMissionPage;
    }
}
