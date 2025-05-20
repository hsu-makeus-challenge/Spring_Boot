package umc.study.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.User;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.UserMissionDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryServiceImpl implements UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserMissionDto> findClearedMissions() {
        return userMissionRepository.findClearedMissionsWithStore();
    }

    @Override
    public List<UserMissionDto> findNotClearedMissions() {
        return userMissionRepository.findNotClearedMissionsWithStore();
    }

    @Override
    public Page<UserMission> getMyClearedMissions(Integer page) {
        User user = userRepository.findById(1L).get();
        return userMissionRepository.findAllByUserAndIsClearedTrue(user, PageRequest.of(page, 10));
    }

    @Override
    public void clearMission(Long userMissionId) {
        UserMission userMission = userMissionRepository.findById(userMissionId).get();
        userMission.setIsCleared(true);
        userMissionRepository.save(userMission);
    }


}
