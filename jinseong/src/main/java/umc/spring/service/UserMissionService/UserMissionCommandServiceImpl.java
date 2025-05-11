package umc.spring.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.StoreMissionHandler;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.converter.UserMissionConverter;
import umc.spring.domain.User;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.StoreMissionRepository.StoreMissionRepository;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;
import umc.spring.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserMissionCommandServiceImpl implements UserMissionCommandService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final StoreMissionRepository storeMissionRepository;

    @Override
    public UserMission createUserMission(Long userId, Long storeMissionId) {
        UserMission newUserMission = UserMissionConverter.toUserMission();

        // 사용자 연관관계 설정

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        newUserMission.setUser(user);

        // 가게 미션 연관관계 설정
        StoreMission storeMission = storeMissionRepository.findById(storeMissionId)
                .orElseThrow(() -> new StoreMissionHandler(ErrorStatus.STORE_MISSION_NOT_FOUND));

        newUserMission.setStoreMission(storeMission);

        return userMissionRepository.save(newUserMission);
    }
}
