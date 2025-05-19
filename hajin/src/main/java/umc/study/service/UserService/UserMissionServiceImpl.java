package umc.study.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Users;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.UserMissionRequestDTO;

@Service
@RequiredArgsConstructor
public class UserMissionServiceImpl implements UserMissionService{

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional //jakarta
    public UserMission addMission(Integer userId, Long missionId, UserMissionRequestDTO.UserMissionDto request) {
        Users user = userRepository.getReferenceById(userId);
        Mission mission = missionRepository.getReferenceById(missionId);
        UserMission userMission = UserMissionConverter.toUserMission(user,mission, request);
        mission.addUserMission(userMission);
        user.addUserMission(userMission);
        return userMissionRepository.save(userMission);
    }
}
