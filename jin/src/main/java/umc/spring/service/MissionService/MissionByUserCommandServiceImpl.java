package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.MissionByUser;
import umc.spring.repository.MissionByUserRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionByUserCommandServiceImpl implements MissionByUserCommandService {

    private final MissionRepository missionRepository;
    private final MissionByUserRepository missionByUserRepository;
    private final UserRepository userRepository;

    @Override
    public MissionByUser challengeMission(Long missionId){

        User user = userRepository.findById(1L).orElse(null);

        // 미션 존재 여부
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // 미션 도전
        MissionByUser missionByUser = MissionByUser.builder()
                .mission(mission)
                .user(user)
                .isCompleted(false)
                .build();

        return missionByUserRepository.save(missionByUser);
    }

}
